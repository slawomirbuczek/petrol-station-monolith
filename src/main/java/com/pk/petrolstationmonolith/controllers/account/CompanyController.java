package com.pk.petrolstationmonolith.controllers.account;

import com.pk.petrolstationmonolith.dtos.account.CompanyDto;
import com.pk.petrolstationmonolith.services.account.CompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/account/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    @PreAuthorize("authenticated()")
    public ResponseEntity<CompanyDto> getCompany(Principal principal) {
        return ResponseEntity.ok(companyService.getCompanyDto(Long.parseLong(principal.getName())));
    }

    @GetMapping("/users/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN','OWNER')")
    public ResponseEntity<CompanyDto> getCompanyDtoByUserId(@PathVariable long userId) {
        return ResponseEntity.ok(companyService.getCompanyDto(userId));
    }

    @PutMapping
    public ResponseEntity<CompanyDto> updateCompany(Principal principal,
                                                    @Valid @RequestBody CompanyDto companyDto) {
        return ResponseEntity.ok(companyService.updateCompany(Long.parseLong(principal.getName()), companyDto));
    }

    @PutMapping("/users/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN','OWNER')")
    public ResponseEntity<CompanyDto> updateCompanyByUserId(@PathVariable long userId,
                                                            @Valid @RequestBody CompanyDto companyDto) {
        return ResponseEntity.ok(companyService.updateCompany(userId, companyDto));
    }

}
