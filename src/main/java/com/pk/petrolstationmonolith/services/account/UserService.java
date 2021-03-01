package com.pk.petrolstationmonolith.services.account;

import com.pk.petrolstationmonolith.dtos.account.UserDto;
import com.pk.petrolstationmonolith.entities.account.EmailToken;
import com.pk.petrolstationmonolith.entities.account.User;
import com.pk.petrolstationmonolith.enums.account.Roles;
import com.pk.petrolstationmonolith.enums.account.UserType;
import com.pk.petrolstationmonolith.exceptions.account.EmailAlreadyTakenException;
import com.pk.petrolstationmonolith.exceptions.account.InvalidEmailTokenException;
import com.pk.petrolstationmonolith.exceptions.account.user.UserNotFoundException;
import com.pk.petrolstationmonolith.models.account.RequestUpdateEmail;
import com.pk.petrolstationmonolith.repositories.account.UserRepository;
import com.pk.petrolstationmonolith.services.emailtoken.EmailTokenService;
import com.pk.petrolstationmonolith.services.mail.MailService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final EmailTokenService emailTokenService;
    private final MailService mailService;
    private final ModelMapper mapper;
    private final PasswordEncoder encoder;

    public UserService(UserRepository userRepository, EmailTokenService emailTokenService, MailService mailService, ModelMapper mapper, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.emailTokenService = emailTokenService;
        this.mailService = mailService;
        this.mapper = mapper;
        this.encoder = encoder;
    }

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
        return mapUserToDto(getUser(userId));
    }

    public User addUser(User user) {
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

    public User changeUserTypeToEmployeeAndSetRole(long userId, Roles role) {
        User user = getUser(userId);
        user.setUserType(UserType.EMPLOYEE);
        user.setRole(role);
        return userRepository.save(user);
    }

    public void activeAccount(long userId) {
        User user = getUser(userId);
        user.setEnabled(true);
        userRepository.save(user);
    }

    public void sendUpdateEmailMail(long userId) {
        User user = getUser(userId);
        EmailToken emailToken = emailTokenService.createNewToken(user);
        mailService.sendEmailUpdateMail(user.getEmail(), emailToken.getToken());
    }

    public void updateEmail(long userId, RequestUpdateEmail request) {
        if (!emailTokenService.validateToken(request.getToken(), userId)) {
            throw new InvalidEmailTokenException();
        }

        User user = getUser(userId);
        user.setEmail(request.getEmail());
        userRepository.save(user);
        emailTokenService.deleteToken(request.getToken());
    }

    private String encodePassword(String password) {
        return encoder.encode(password);
    }

    private UserDto mapUserToDto(User user) {
        return mapper.map(user, UserDto.class);
    }

}
