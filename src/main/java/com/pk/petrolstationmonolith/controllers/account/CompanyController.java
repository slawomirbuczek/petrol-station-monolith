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
    @PreAuthorize("hasRole('CUSTOMER_COMPANY')")
    public ResponseEntity<CompanyDto> getCompany(Principal principal) {
        return ResponseEntity.ok(companyService.getCompanyDto(Long.parseLong(principal.getName())));
    }

    @GetMapping("/customers/{customerId}")
    @PreAuthorize("hasAnyRole('ADMIN','OWNER')")
    public ResponseEntity<CompanyDto> getCompanyDtoByUserId(@PathVariable long customerId) {
        return ResponseEntity.ok(companyService.getCompanyDto(customerId));
    }

    @PutMapping
    @PreAuthorize("hasRole('CUSTOMER_COMPANY')")
    public ResponseEntity<CompanyDto> updateCompany(Principal principal,
                                                    @Valid @RequestBody CompanyDto companyDto) {
        return ResponseEntity.ok(companyService.updateCompany(Long.parseLong(principal.getName()), companyDto));
    }

    @PutMapping("/customers/{customerId}")
    @PreAuthorize("hasAnyRole('ADMIN','OWNER')")
    public ResponseEntity<CompanyDto> updateCompanyByUserId(@PathVariable long customerId,
                                                            @Valid @RequestBody CompanyDto companyDto) {
        return ResponseEntity.ok(companyService.updateCompany(customerId, companyDto));
    }

}
