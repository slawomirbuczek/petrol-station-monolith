package com.pk.petrolstationmonolith.controllers.account;

import com.pk.petrolstationmonolith.dtos.account.UserDto;
import com.pk.petrolstationmonolith.models.ResponseMessage;
import com.pk.petrolstationmonolith.models.account.UserCredentials;
import com.pk.petrolstationmonolith.models.account.password.RequestNewPassword;
import com.pk.petrolstationmonolith.models.account.password.RequestResetPassword;
import com.pk.petrolstationmonolith.models.account.password.RequestUpdatePassword;
import com.pk.petrolstationmonolith.models.account.registration.CompanyRegistrationCredentials;
import com.pk.petrolstationmonolith.models.account.registration.EmployeeRegistrationCredentials;
import com.pk.petrolstationmonolith.models.account.registration.IndividualRegistrationCredentials;
import com.pk.petrolstationmonolith.services.account.PasswordService;
import com.pk.petrolstationmonolith.services.account.RegistrationService;
import com.pk.petrolstationmonolith.services.account.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final Logger logger = LoggerFactory.getLogger(AccountController.class);
    private final RegistrationService registrationService;
    private final PasswordService passwordService;
    private final UserService userService;

    public AccountController(RegistrationService registrationService, PasswordService passwordService, UserService userService) {
        this.registrationService = registrationService;
        this.passwordService = passwordService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public void login(@RequestBody com.pk.petrolstationmonolith.models.account.UserCredentials credentials) {
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserDto> getUserDto(@PathVariable Long userId) {
        logger.trace("getUser method invoked");
        return ResponseEntity.ok(userService.getUserDto(userId));
    }

    @PostMapping("/individuals")
    public ResponseEntity<UserCredentials> registerIndividual(@Valid @RequestBody IndividualRegistrationCredentials credentials) {
        logger.trace("registerIndividual method invoked");
        return ResponseEntity.ok(registrationService.registerIndividual(credentials));
    }

    @PostMapping("/companies")
    public ResponseEntity<UserCredentials> registerCompany(@Valid @RequestBody CompanyRegistrationCredentials credentials) {
        logger.trace("registerCompany method invoked");
        return ResponseEntity.ok(registrationService.registerCompany(credentials));
    }

    @PostMapping("/employees")
    public ResponseEntity<UserCredentials> registerEmployee(@Valid @RequestBody EmployeeRegistrationCredentials credentials) {
        logger.trace("registerEmployee method invoked");
        return ResponseEntity.ok(registrationService.registerEmployee(credentials));
    }


    @PutMapping("/password")
    public ResponseEntity<ResponseMessage> updatePassword(@Valid @RequestBody RequestUpdatePassword request,
                                                          Principal principal) {
        logger.trace("updatePassword method invoked");
        return ResponseEntity.ok(passwordService.updatePassword(request, principal));
    }

    @DeleteMapping("/password")
    public ResponseEntity<ResponseMessage> sendResetPasswordMail(@Valid @RequestBody RequestResetPassword request) {
        logger.trace("sendResetPasswordMail method invoked");
        return ResponseEntity.ok(passwordService.sendPasswordResetMail(request));
    }

    @PostMapping("/password")
    public ResponseEntity<ResponseMessage> setNewPassword(
            @Valid @RequestBody RequestNewPassword request,
            @RequestParam(name = "token") String token,
            @RequestParam(name = "email") String email

    ) {
        logger.trace("setNewPassword method invoked");
        return ResponseEntity.ok(passwordService.setNewPassword(request, token, email));
    }


}
