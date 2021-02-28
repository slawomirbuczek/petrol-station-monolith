package com.pk.petrolstationmonolith.controllers.account;

import com.pk.petrolstationmonolith.dtos.account.AddressDto;
import com.pk.petrolstationmonolith.services.account.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/account/addresses")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public ResponseEntity<AddressDto> getAddress(Principal principal) {
        return ResponseEntity.ok(addressService.getAddressDto(Long.parseLong(principal.getName())));
    }

    @GetMapping("/users/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN','OWNER')")
    public ResponseEntity<AddressDto> getAddressDtoByUserId(@PathVariable long userId) {
        return ResponseEntity.ok(addressService.getAddressDto(userId));
    }

    @PutMapping
    public ResponseEntity<AddressDto> updateAddress(Principal principal,
                                                            @Valid @RequestBody AddressDto addressDto) {
        return ResponseEntity.ok(addressService.updateAddress(Long.parseLong(principal.getName()), addressDto));
    }

    @PutMapping("/users/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN','OWNER')")
    public ResponseEntity<AddressDto> updateAddressByUserId(@PathVariable long userId,
                                                            @Valid @RequestBody AddressDto addressDto) {
        return ResponseEntity.ok(addressService.updateAddress(userId, addressDto));
    }

}
