package com.pk.petrolstationmonolith.controllers;

import com.pk.petrolstationmonolith.models.ResponseMessage;
import com.pk.petrolstationmonolith.models.registration.CompanyRegistrationCredentials;
import com.pk.petrolstationmonolith.models.registration.EmployeeRegistrationCredentials;
import com.pk.petrolstationmonolith.models.registration.IndividualRegistrationCredentials;
import com.pk.petrolstationmonolith.models.user.RequestUpdatePassword;
import com.pk.petrolstationmonolith.models.user.UserCredentials;
import com.pk.petrolstationmonolith.services.user.RegistrationService;
import com.pk.petrolstationmonolith.services.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final RegistrationService registrationService;
    private final UserService userService;

    public AccountController(RegistrationService registrationService, UserService userService) {
        this.registrationService = registrationService;
        this.userService = userService;
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

    @PutMapping("/users/{id}/password")
    public ResponseEntity<ResponseMessage> updatePassword(
            @PathVariable String id,
            @Valid @RequestBody RequestUpdatePassword request) {
        return ResponseEntity.ok(userService.updatePassword(request, id));
    }


}
