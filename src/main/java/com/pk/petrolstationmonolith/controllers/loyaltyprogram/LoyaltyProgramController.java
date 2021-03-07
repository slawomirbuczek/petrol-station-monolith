package com.pk.petrolstationmonolith.controllers.loyaltyprogram;

import com.pk.petrolstationmonolith.enums.ServiceType;
import com.pk.petrolstationmonolith.models.loyaltyprogram.Points;
import com.pk.petrolstationmonolith.models.loyaltyprogram.ProgramParameters;
import com.pk.petrolstationmonolith.models.loyaltyprogram.RequestChangeProgramParameters;
import com.pk.petrolstationmonolith.services.loyaltyprogram.LoyaltyProgramService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/loayltyprogram")
@AllArgsConstructor
public class LoyaltyProgramController {

    private final LoyaltyProgramService loyaltyProgramService;

    @PostMapping
    public ResponseEntity<Points> joinLoyaltyProgram(Principal principal) {
        return ResponseEntity.ok(loyaltyProgramService.joinLoyaltyProgram(Long.parseLong(principal.getName())));
    }

    @GetMapping("/points")
    public ResponseEntity<Points> getPoints(Principal principal) {
        return ResponseEntity.ok(loyaltyProgramService.getPoints(Long.parseLong(principal.getName())));
    }

    @GetMapping("/points/customers/{customerId}")
    @PreAuthorize("hasAnyRole('CASHIER','ADMIN','OWNER')")
    public ResponseEntity<Points> getPointsByCustomer(@PathVariable long customerId) {
        return ResponseEntity.ok(loyaltyProgramService.getPoints(customerId));
    }

    @PutMapping("/customers/{customerId}/services/{serviceType}")
    @PreAuthorize("hasAnyRole('CASHIER','ADMIN','OWNER')")
    public ResponseEntity<Points> addPoints(
            @PathVariable long customerId,
            @PathVariable ServiceType serviceType,
            @Valid @RequestBody Points points) {
        return ResponseEntity.ok(loyaltyProgramService.addProgramPoints(customerId, serviceType, points));
    }

    @PostMapping("/services/{serviceType}")
    public ResponseEntity<Points> exchangePoints(
            @PathVariable ServiceType serviceType,
            @Valid @RequestBody Points points,
            Principal principal) {
        return ResponseEntity.ok(loyaltyProgramService.exchangePoints(Long.parseLong(principal.getName()), serviceType, points));
    }

    @PutMapping("/services/{serviceType}")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<ProgramParameters> changeProgramParameters(
            @PathVariable ServiceType serviceType,
            @Valid @RequestBody RequestChangeProgramParameters request) {
        return ResponseEntity.ok(loyaltyProgramService.changeProgramParameters(serviceType, request));
    }

    @GetMapping("/services")
    public ResponseEntity<ProgramParameters> getProgramParameters() {
        return ResponseEntity.ok(loyaltyProgramService.getProgramParameters());
    }

}
