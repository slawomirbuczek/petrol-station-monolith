package com.pk.petrolstationmonolith.services.account;

import com.pk.petrolstationmonolith.dtos.account.UserDto;
import com.pk.petrolstationmonolith.entities.emailtoken.EmailToken;
import com.pk.petrolstationmonolith.entities.account.User;
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

    public User getUser(long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    public User getUser(String userId) {
        try {
            return getUser(Long.parseLong(userId));
        } catch (Exception e) {
            throw new UserNotFoundException(userId);
        }
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
    }

    public UserDto getUserDto(long userId) {
        log.trace("Getting user dto for user with id " + userId);
        return mapUserToDto(getUser(userId));
    }

    public User addUser(User user) {
        log.trace("Adding new user with email " + user.getEmail() + "and role " + user.getRole());
        user.setPassword(encodePassword(user.getPassword()));
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyTakenException(user.getEmail());
        }
        return userRepository.save(user);
    }

    public void updatePassword(User user, String newPassword) {
        user.setPassword(encodePassword(newPassword));
        userRepository.save(user);
    }

    public boolean passwordMatches(String encodedPassword, String password) {
        return encoder.matches(password, encodedPassword);
    }

    public User changeUserRole(long userId, Roles role) {
        log.trace("Changing user with id " + userId + " role to " + role);
        User user = getUser(userId);
        user.setRole(role);
        return userRepository.save(user);
    }

    public void activeAccount(long userId) {
        log.trace("Activiating account for user with id " + userId);
        User user = getUser(userId);
        user.setEnabled(true);
        userRepository.save(user);
    }

    public void sendUpdateEmailMail(long userId) {
        log.trace("Sending update email to user with id " + userId);
        User user = getUser(userId);
        EmailToken emailToken = emailTokenService.createNewToken(user);
        mailService.sendEmailUpdateMail(user.getEmail(), emailToken.getToken());
    }

    public void updateEmail(long userId, RequestUpdateEmail request) {
        log.trace("Updating email for user with id " + userId);
        if (emailTokenService.tokenNotValid(request.getToken(), userId)) {
            throw new InvalidEmailTokenException();
        }

        User user = getUser(userId);
        user.setEmail(request.getEmail());
        userRepository.save(user);
        emailTokenService.deleteToken(request.getToken());
    }

    public UserDto disableUser(long userId) {
        log.trace("Disabling user with id " + userId);
        User user = getUser(userId);
        user.setEnabled(false);
        user = userRepository.save(user);
        return mapUserToDto(user);
    }

    private String encodePassword(String password) {
        return encoder.encode(password);
    }

    private UserDto mapUserToDto(User user) {
        return mapper.map(user, UserDto.class);
    }

}
