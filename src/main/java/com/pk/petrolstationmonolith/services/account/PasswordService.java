package com.pk.petrolstationmonolith.services.account;

import com.pk.petrolstationmonolith.entities.emailtoken.EmailToken;
import com.pk.petrolstationmonolith.entities.account.User;
import com.pk.petrolstationmonolith.exceptions.account.InvalidEmailTokenException;
import com.pk.petrolstationmonolith.exceptions.account.InvalidPasswordException;
import com.pk.petrolstationmonolith.models.ResponseMessage;
import com.pk.petrolstationmonolith.models.account.password.RequestNewPassword;
import com.pk.petrolstationmonolith.models.account.password.RequestResetPassword;
import com.pk.petrolstationmonolith.models.account.password.RequestUpdatePassword;
import com.pk.petrolstationmonolith.services.emailtoken.EmailTokenService;
import com.pk.petrolstationmonolith.services.mail.MailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class PasswordService {

    private final UserService userService;
    private final EmailTokenService emailTokenService;
    private final MailService mailService;

    public ResponseMessage updatePassword(RequestUpdatePassword request, long userId) {
        log.trace("Updating password for user with id " + userId);
        User user = userService.getUser(userId);

        if (!userService.passwordMatches(user.getPassword(), request.getOldPassword())) {
            throw new InvalidPasswordException();
        }

        userService.updatePassword(user, request.getNewPassword());
        return new ResponseMessage("Password successfully updated.");
    }

    public ResponseMessage sendPasswordResetMail(RequestResetPassword request) {
        log.trace("Sending password reset mail for user with email " + request.getEmail());
        User user = userService.getUserByEmail(request.getEmail());

        EmailToken emailToken = emailTokenService.createNewToken(user);

        mailService.sendPasswordResetMail(user.getEmail(), emailToken.getToken());

        return new ResponseMessage("Reset password email has been sent.");
    }

    public ResponseMessage setNewPassword(RequestNewPassword request) {
        log.trace("Setting new password for user with email " + request.getEmail());
        User user = userService.getUserByEmail(request.getEmail());

        if (emailTokenService.tokenNotValid(request.getToken(), user.getId())) {
            throw new InvalidEmailTokenException();
        }

        userService.updatePassword(user, request.getNewPassword());
        emailTokenService.deleteToken(request.getToken());

        return new ResponseMessage("New password has been set.");
    }

}
