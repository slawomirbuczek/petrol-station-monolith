package com.pk.petrolstationmonolith.controllers.account;

import com.pk.petrolstationmonolith.dtos.account.AddressDto;
import com.pk.petrolstationmonolith.services.account.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/account/addresses")
@AllArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @GetMapping
    public ResponseEntity<AddressDto> getAddress(Principal principal) {
        return ResponseEntity.ok(addressService.getAddressDto(Long.parseLong(principal.getName())));
    }

    @GetMapping("/customers/{customerId}")
    @PreAuthorize("hasAnyRole('ADMIN','OWNER')")
    public ResponseEntity<AddressDto> getAddressDtoByUserId(@PathVariable long customerId) {
        return ResponseEntity.ok(addressService.getAddressDto(customerId));
    }

    @PutMapping
    public ResponseEntity<AddressDto> updateAddress(Principal principal, @Valid @RequestBody AddressDto addressDto) {
        return ResponseEntity.ok(addressService.updateAddress(Long.parseLong(principal.getName()), addressDto));
    }

    @PutMapping("/customers/{customerId}")
    @PreAuthorize("hasAnyRole('ADMIN','OWNER')")
    public ResponseEntity<AddressDto> updateAddressByUserId(@PathVariable long customerId,
                                                            @Valid @RequestBody AddressDto addressDto) {
        return ResponseEntity.ok(addressService.updateAddress(customerId, addressDto));
    }

}
