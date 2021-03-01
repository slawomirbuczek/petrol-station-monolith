package com.pk.petrolstationmonolith.services.account;

import com.pk.petrolstationmonolith.entities.account.EmailToken;
import com.pk.petrolstationmonolith.entities.account.User;
import com.pk.petrolstationmonolith.exceptions.account.InvalidEmailTokenException;
import com.pk.petrolstationmonolith.exceptions.account.InvalidPasswordException;
import com.pk.petrolstationmonolith.models.ResponseMessage;
import com.pk.petrolstationmonolith.models.account.password.RequestNewPassword;
import com.pk.petrolstationmonolith.models.account.password.RequestResetPassword;
import com.pk.petrolstationmonolith.models.account.password.RequestUpdatePassword;
import com.pk.petrolstationmonolith.services.emailtoken.EmailTokenService;
import com.pk.petrolstationmonolith.services.mail.MailService;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {

    private final UserService userService;
    private final EmailTokenService emailTokenService;
    private final MailService mailService;

    public PasswordService(UserService userService, EmailTokenService emailTokenService, MailService mailService) {
        this.userService = userService;
        this.emailTokenService = emailTokenService;
        this.mailService = mailService;
    }

    public ResponseMessage updatePassword(RequestUpdatePassword request, long userId) {
        User user = userService.getUser(userId);

        if (!userService.passwordMatches(user.getPassword(), request.getOldPassword())) {
            throw new InvalidPasswordException();
        }

        userService.updatePassword(user, request.getNewPassword());
        return new ResponseMessage("Password successfully updated.");
    }

    public ResponseMessage sendPasswordResetMail(RequestResetPassword request) {
        User user = userService.getUserByEmail(request.getEmail());

        EmailToken emailToken = emailTokenService.createNewToken(user);

        mailService.sendPasswordResetMail(user.getEmail(), emailToken.getToken());

        return new ResponseMessage("Reset password email has been sent.");
    }

    public ResponseMessage setNewPassword(RequestNewPassword request) {
        User user = userService.getUserByEmail(request.getEmail());

        if (!emailTokenService.validateToken(request.getToken(), user.getId())) {
            throw new InvalidEmailTokenException();
        }

        userService.updatePassword(user, request.getNewPassword());
        emailTokenService.deleteToken(request.getToken());

        return new ResponseMessage("New password has been set.");
    }

}
