package com.pk.petrolstationmonolith.controllers.account;

import com.pk.petrolstationmonolith.dtos.account.CompanyDto;
import com.pk.petrolstationmonolith.services.account.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/account/companies")
@AllArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping
    @PreAuthorize("hasRole('USER_COMPANY')")
    public ResponseEntity<CompanyDto> getCompany(Principal principal) {
        return ResponseEntity.ok(companyService.getCompanyDto(Long.parseLong(principal.getName())));
    }

    @GetMapping("/users/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN','OWNER')")
    public ResponseEntity<CompanyDto> getCompanyDtoByUserId(@PathVariable long userId) {
        return ResponseEntity.ok(companyService.getCompanyDto(userId));
    }

    @PutMapping
    @PreAuthorize("hasRole('USER_COMPANY')")
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
