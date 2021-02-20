package com.pk.petrolstationmonolith.services.account;

import com.pk.petrolstationmonolith.entities.account.EmailToken;
import com.pk.petrolstationmonolith.entities.account.User;
import com.pk.petrolstationmonolith.exceptions.account.EmailNotFoundException;
import com.pk.petrolstationmonolith.exceptions.account.InvalidEmailTokenException;
import com.pk.petrolstationmonolith.exceptions.account.InvalidPasswordException;
import com.pk.petrolstationmonolith.models.ResponseMessage;
import com.pk.petrolstationmonolith.models.account.password.RequestNewPassword;
import com.pk.petrolstationmonolith.models.account.password.RequestResetPassword;
import com.pk.petrolstationmonolith.models.account.password.RequestUpdatePassword;
import com.pk.petrolstationmonolith.repositories.account.EmailTokenRepository;
import com.pk.petrolstationmonolith.repositories.account.UserRepository;
import com.pk.petrolstationmonolith.services.mail.MailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PasswordService {

    private final UserRepository userRepository;
    private final EmailTokenRepository emailTokenRepository;
    private final MailService mailService;
    private final PasswordEncoder passwordEncoder;

    public PasswordService(UserRepository userRepository, EmailTokenRepository emailTokenRepository,
                           MailService mailService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.emailTokenRepository = emailTokenRepository;
        this.mailService = mailService;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseMessage updatePassword(RequestUpdatePassword request) {

        long id = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());

        User user = userRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User with id: " + id + " not found.")
        );

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new InvalidPasswordException();
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        userRepository.save(user);

        return new ResponseMessage("Password successfully updated.");
    }

    public ResponseMessage sendPasswordResetMail(RequestResetPassword request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new EmailNotFoundException(request.getEmail())
        );

        UUID token = UUID.randomUUID();

        EmailToken emailToken = new EmailToken();
        emailToken.setToken(token);
        emailToken.setUser(user);

        emailTokenRepository.save(emailToken);

        mailService.sendPasswordResetEmail(user.getEmail(), token);

        return new ResponseMessage("Reset password email has been sent.");
    }

    public ResponseMessage setNewPassword(RequestNewPassword request, String token, String email) {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new EmailNotFoundException(email)
        );

        EmailToken emailToken = emailTokenRepository.findByToken(UUID.fromString(token))
                .orElseThrow(InvalidEmailTokenException::new);

        if (emailToken.getUser().getId().equals(user.getId())) {
            user.setPassword(passwordEncoder.encode(request.getNewPassword()));
            userRepository.save(user);
            emailTokenRepository.delete(emailToken);
            return new ResponseMessage("New password has been set.");
        } else {
            throw new InvalidEmailTokenException();
        }

    }

}
