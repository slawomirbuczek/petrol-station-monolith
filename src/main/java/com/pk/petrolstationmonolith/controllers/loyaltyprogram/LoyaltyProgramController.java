package com.pk.petrolstationmonolith.controllers.loyaltyprogram;

import com.pk.petrolstationmonolith.enums.ServiceType;
import com.pk.petrolstationmonolith.models.loyaltyprogram.Points;
import com.pk.petrolstationmonolith.models.loyaltyprogram.ProgramParameters;
import com.pk.petrolstationmonolith.models.loyaltyprogram.RequestChangeProgramParameters;
import com.pk.petrolstationmonolith.services.loyaltyprogram.LoyaltyProgramService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/loayltyprogram")
public class LoyaltyProgramController {

    private final Logger logger = LoggerFactory.getLogger(LoyaltyProgramController.class);
    private final LoyaltyProgramService loyaltyProgramService;

    public LoyaltyProgramController(LoyaltyProgramService loyaltyProgramService) {
        this.loyaltyProgramService = loyaltyProgramService;
    }

    @PostMapping
    public ResponseEntity<Points> joinLoyaltyProgram(Principal principal) {
        logger.trace("joinLoyaltyProgram method invoked");
        return ResponseEntity.ok(loyaltyProgramService.joinLoyaltyProgram(Long.parseLong(principal.getName())));
    }

    @GetMapping("/points")
    public ResponseEntity<Points> getPoints(Principal principal) {
        logger.trace("getPoints method invoked");
        return ResponseEntity.ok(loyaltyProgramService.getPoints(Long.parseLong(principal.getName())));
    }

    @GetMapping("/points/users/{userId}")
    @PreAuthorize("hasAnyRole('CASHIER','ADMIN','OWNER')")
    public ResponseEntity<Points> getPointsByUser(@PathVariable long userId) {
        logger.trace("getPointsByUser method invoked");
        return ResponseEntity.ok(loyaltyProgramService.getPoints(userId));
    }

    @PutMapping("/users/{userId}/services/{serviceType}")
    @PreAuthorize("hasAnyRole('CASHIER','ADMIN','OWNER')")
    public ResponseEntity<Points> addPoints(
            @PathVariable long userId,
            @PathVariable ServiceType serviceType,
            @Valid @RequestBody Points points) {
        logger.trace("addPoints method invoked");
        return ResponseEntity.ok(loyaltyProgramService.addProgramPoints(userId, serviceType, points));
    }

    @PostMapping("/services/{serviceType}")
    public ResponseEntity<Points> exchangePoints(
            @PathVariable ServiceType serviceType,
            @Valid @RequestBody Points points,
            Principal principal) {
        logger.trace("exchangePoints method invoked");
        return ResponseEntity.ok(loyaltyProgramService.exchangePoints(Long.parseLong(principal.getName()), serviceType, points));
    }

    @PutMapping("/services/{serviceType}")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<ProgramParameters> changeProgramParameters(
            @PathVariable ServiceType serviceType,
            @Valid @RequestBody RequestChangeProgramParameters request) {
        logger.trace("changeProgramParameters method invoked");
        return ResponseEntity.ok(loyaltyProgramService.changeProgramParameters(serviceType, request));
    }

    @GetMapping("/services")
    public ResponseEntity<ProgramParameters> getProgramParameters() {
        logger.trace("getProgramParameters method invoked");
        return ResponseEntity.ok(loyaltyProgramService.getProgramParameters());
    }

}