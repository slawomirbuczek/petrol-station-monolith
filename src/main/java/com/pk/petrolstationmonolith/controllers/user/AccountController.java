package com.pk.petrolstationmonolith.controllers.user;

import com.pk.petrolstationmonolith.models.ResponseMessage;
import com.pk.petrolstationmonolith.models.password.RequestNewPassword;
import com.pk.petrolstationmonolith.models.password.RequestResetPassword;
import com.pk.petrolstationmonolith.models.password.RequestUpdatePassword;
import com.pk.petrolstationmonolith.models.registration.CompanyRegistrationCredentials;
import com.pk.petrolstationmonolith.models.registration.EmployeeRegistrationCredentials;
import com.pk.petrolstationmonolith.models.registration.IndividualRegistrationCredentials;
import com.pk.petrolstationmonolith.models.user.UserCredentials;
import com.pk.petrolstationmonolith.services.user.PasswordService;
import com.pk.petrolstationmonolith.services.user.RegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final RegistrationService registrationService;
    private final PasswordService passwordService;

    public AccountController(RegistrationService registrationService, PasswordService passwordService) {
        this.registrationService = registrationService;
        this.passwordService = passwordService;
    }

    @PostMapping("/login")
    public void login(@RequestBody com.pk.petrolstationmonolith.models.user.UserCredentials credentials) {

    }

    @PostMapping("/individuals")
    public ResponseEntity<UserCredentials> registerIndividual(@Valid @RequestBody IndividualRegistrationCredentials credentials) {
        return ResponseEntity.ok(registrationService.registerIndividual(credentials));
    }

    @PostMapping("/companies")
    public ResponseEntity<UserCredentials> registerCompany(@Valid @RequestBody CompanyRegistrationCredentials credentials) {
        return ResponseEntity.ok(registrationService.registerCompany(credentials));
    }

    @PostMapping("/employees")
    public ResponseEntity<UserCredentials> registerEmployee(@Valid @RequestBody EmployeeRegistrationCredentials credentials) {
        return ResponseEntity.ok(registrationService.registerEmployee(credentials));
    }


    @PutMapping("/password")
    public ResponseEntity<ResponseMessage> updatePassword(@Valid @RequestBody RequestUpdatePassword request) {
        return ResponseEntity.ok(passwordService.updatePassword(request));
    }

    @DeleteMapping("/password")
    public ResponseEntity<ResponseMessage> sendResetPasswordMail(@Valid @RequestBody RequestResetPassword request) {
        return ResponseEntity.ok(passwordService.sendPasswordResetMail(request));
    }

    @PostMapping("/password")
    public ResponseEntity<ResponseMessage> setNewPassword(
            @Valid @RequestBody RequestNewPassword request,
            @RequestParam(name = "token") String token,
            @RequestParam(name = "email") String email

    ) {
        return ResponseEntity.ok(passwordService.setNewPassword(request, token, email));
    }



}
