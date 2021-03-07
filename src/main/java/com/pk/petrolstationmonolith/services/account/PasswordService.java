package com.pk.petrolstationmonolith.services.account;

import com.pk.petrolstationmonolith.entities.account.Customers;
import com.pk.petrolstationmonolith.entities.emailtoken.EmailToken;
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

    private final CustomerService customerService;
    private final EmailTokenService emailTokenService;
    private final MailService mailService;

    public ResponseMessage updatePassword(RequestUpdatePassword request, long customerId) {
        log.trace("Updating password for customer with id " + customerId);
        Customers customers = customerService.getCustomer(customerId);

        if (!customerService.passwordMatches(customers.getPassword(), request.getOldPassword())) {
            throw new InvalidPasswordException();
        }

        customerService.updatePassword(customers, request.getNewPassword());
        return new ResponseMessage("Password successfully updated.");
    }

    public ResponseMessage sendPasswordResetMail(RequestResetPassword request) {
        log.trace("Sending password reset mail for customer with email " + request.getEmail());
        Customers customers = customerService.getCustomerByEmail(request.getEmail());

        EmailToken emailToken = emailTokenService.createNewToken(customers);

        mailService.sendPasswordResetMail(customers.getEmail(), emailToken.getToken());

        return new ResponseMessage("Reset password email has been sent.");
    }

    public ResponseMessage setNewPassword(RequestNewPassword request) {
        log.trace("Setting new password for customer with email " + request.getEmail());
        Customers customers = customerService.getCustomerByEmail(request.getEmail());

        if (emailTokenService.tokenNotValid(request.getToken(), customers.getId())) {
            throw new InvalidEmailTokenException();
        }

        customerService.updatePassword(customers, request.getNewPassword());
        emailTokenService.deleteToken(request.getToken());

        return new ResponseMessage("New password has been set.");
    }

}
