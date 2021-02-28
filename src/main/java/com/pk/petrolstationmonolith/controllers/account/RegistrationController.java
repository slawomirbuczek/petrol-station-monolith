package com.pk.petrolstationmonolith.controllers.account;

import com.pk.petrolstationmonolith.dtos.account.EmployeeDto;
import com.pk.petrolstationmonolith.models.account.UserCredentials;
import com.pk.petrolstationmonolith.models.account.registration.CompanyRegistrationCredentials;
import com.pk.petrolstationmonolith.models.account.registration.EmployeeRegistrationCredentials;
import com.pk.petrolstationmonolith.models.account.registration.IndividualRegistrationCredentials;
import com.pk.petrolstationmonolith.services.account.RegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/account/registration")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
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
    @PreAuthorize("hasAnyRole('ADMIN','OWNER')")
    public ResponseEntity<EmployeeDto> registerEmployee(@Valid @RequestBody EmployeeRegistrationCredentials credentials) {
        return ResponseEntity.ok(registrationService.registerEmployee(credentials));
    }

}
