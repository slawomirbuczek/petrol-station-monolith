package com.pk.petrolstationmonolith.controllers.account;

import com.pk.petrolstationmonolith.dtos.account.CustomerDto;
import com.pk.petrolstationmonolith.models.account.RequestUpdateEmail;
import com.pk.petrolstationmonolith.models.account.CustomerCredentials;
import com.pk.petrolstationmonolith.services.account.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/login")
    public void login(@RequestBody CustomerCredentials credentials) {
    }

    @GetMapping("/customers/{customerId}")
    @PreAuthorize("hasAnyRole('CASHIER','ADMIN','OWNER')")
    public ResponseEntity<CustomerDto> getCustomerDtoByCustomerId(@PathVariable Long customerId) {
        return ResponseEntity.ok(customerService.getCustomerDto(customerId));
    }

    @GetMapping("/customers")
    public ResponseEntity<CustomerDto> getCustomerDto(Principal principal) {
        return ResponseEntity.ok(customerService.getCustomerDto(Long.parseLong(principal.getName())));
    }

    @PostMapping("/email")
    @ResponseStatus(HttpStatus.OK)
    public void sendUpdateEmailMail(Principal principal) {
        customerService.sendUpdateEmailMail(Long.parseLong(principal.getName()));
    }

    @PutMapping("/email")
    @ResponseStatus(HttpStatus.OK)
    public void updateEmail(@Valid @RequestBody RequestUpdateEmail request, Principal principal) {
        customerService.updateEmail(Long.parseLong(principal.getName()), request);
    }

    @DeleteMapping("/customers")
    public ResponseEntity<CustomerDto> disableCustomer(Principal principal) {
        return ResponseEntity.ok(customerService.disableCustomer(Long.parseLong(principal.getName())));
    }

    @DeleteMapping("/customers/{customerId}")
    @PreAuthorize("hasAnyRole('ADMIN','OWNER')")
    public ResponseEntity<CustomerDto> disableCustomerById(@PathVariable long customerId) {
        return ResponseEntity.ok(customerService.disableCustomer(customerId));
    }



}
