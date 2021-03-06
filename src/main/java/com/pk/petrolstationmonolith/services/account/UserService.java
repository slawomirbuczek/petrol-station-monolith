package com.pk.petrolstationmonolith.services.account;

import com.pk.petrolstationmonolith.dtos.account.UserDto;
import com.pk.petrolstationmonolith.entities.account.Customers;
import com.pk.petrolstationmonolith.entities.emailtoken.EmailToken;
import com.pk.petrolstationmonolith.enums.Roles;
import com.pk.petrolstationmonolith.exceptions.account.EmailAlreadyTakenException;
import com.pk.petrolstationmonolith.exceptions.account.InvalidEmailTokenException;
import com.pk.petrolstationmonolith.exceptions.account.user.UserNotFoundException;
import com.pk.petrolstationmonolith.models.account.RequestUpdateEmail;
import com.pk.petrolstationmonolith.repositories.account.UserRepository;
import com.pk.petrolstationmonolith.services.emailtoken.EmailTokenService;
import com.pk.petrolstationmonolith.services.mail.MailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final EmailTokenService emailTokenService;
    private final MailService mailService;
    private final ModelMapper mapper;
    private final PasswordEncoder encoder;

    public Customers getUser(long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    public Customers getUser(String userId) {
        try {
            return getUser(Long.parseLong(userId));
        } catch (Exception e) {
            throw new UserNotFoundException(userId);
        }
    }

    public Customers getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
    }

    public UserDto getUserDto(long userId) {
        log.trace("Getting user dto for user with id " + userId);
        return mapUserToDto(getUser(userId));
    }

    public Customers addUser(Customers customers) {
        log.trace("Adding new user with email " + customers.getEmail() + "and role " + customers.getRole());
        customers.setPassword(encodePassword(customers.getPassword()));
        if (userRepository.existsByEmail(customers.getEmail())) {
            throw new EmailAlreadyTakenException(customers.getEmail());
        }
        return userRepository.save(customers);
    }

    public void updatePassword(Customers customers, String newPassword) {
        customers.setPassword(encodePassword(newPassword));
        userRepository.save(customers);
    }

    public boolean passwordMatches(String encodedPassword, String password) {
        return encoder.matches(password, encodedPassword);
    }

    public Customers changeUserRole(long userId, Roles role) {
        log.trace("Changing user with id " + userId + " role to " + role);
        Customers customers = getUser(userId);
        customers.setRole(role);
        return userRepository.save(customers);
    }

    public void activeAccount(long userId) {
        log.trace("Activiating account for user with id " + userId);
        Customers customers = getUser(userId);
        customers.setEnabled(true);
        userRepository.save(customers);
    }

    public void sendUpdateEmailMail(long userId) {
        log.trace("Sending update email to user with id " + userId);
        Customers customers = getUser(userId);
        EmailToken emailToken = emailTokenService.createNewToken(customers);
        mailService.sendEmailUpdateMail(customers.getEmail(), emailToken.getToken());
    }

    public void updateEmail(long userId, RequestUpdateEmail request) {
        log.trace("Updating email for user with id " + userId);
        if (emailTokenService.tokenNotValid(request.getToken(), userId)) {
            throw new InvalidEmailTokenException();
        }

        Customers customers = getUser(userId);
        customers.setEmail(request.getEmail());
        userRepository.save(customers);
        emailTokenService.deleteToken(request.getToken());
    }

    public UserDto disableUser(long userId) {
        log.trace("Disabling user with id " + userId);
        Customers customers = getUser(userId);
        customers.setEnabled(false);
        customers = userRepository.save(customers);
        return mapUserToDto(customers);
    }

    private String encodePassword(String password) {
        return encoder.encode(password);
    }

    private UserDto mapUserToDto(Customers customers) {
        return mapper.map(customers, UserDto.class);
    }

}
