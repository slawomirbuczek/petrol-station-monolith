package com.pk.petrolstationmonolith.controllers.layaltyprogram;

import com.pk.petrolstationmonolith.enums.pricelist.ServiceType;
import com.pk.petrolstationmonolith.models.loyaltyprogram.Points;
import com.pk.petrolstationmonolith.models.loyaltyprogram.RequestChangeProgramParameters;
import com.pk.petrolstationmonolith.models.loyaltyprogram.RequestPointsChange;
import com.pk.petrolstationmonolith.models.loyaltyprogram.ResponseProgramParameters;
import com.pk.petrolstationmonolith.services.loyaltyprogram.LoyaltyProgramService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/loayltyprogram")
public class LoyaltyProgramController {

    private final LoyaltyProgramService loyaltyProgramService;

    public LoyaltyProgramController(LoyaltyProgramService loyaltyProgramService) {
        this.loyaltyProgramService = loyaltyProgramService;
    }

    @PostMapping
    public ResponseEntity<Points> joinLoyaltyProgram() {
        return ResponseEntity.ok(loyaltyProgramService.joinLoyaltyProgram());
    }

    @GetMapping("/points")
    public ResponseEntity<Points> getPoints() {
        return ResponseEntity.ok(loyaltyProgramService.getPoints());
    }

    @GetMapping("/points/{userId}")
    public ResponseEntity<Points> getPointsByUser(@PathVariable long userId) {
        return ResponseEntity.ok(loyaltyProgramService.getPointsByUser(userId));
    }

    @PutMapping("/users/{userId}/{serviceType}")
    public ResponseEntity<Points> addPoints(
            @PathVariable long userId,
            @PathVariable ServiceType serviceType,
            @Valid @RequestBody RequestPointsChange request) {
        return ResponseEntity.ok(loyaltyProgramService.addProgramPoints(userId, serviceType, request));
    }

    @PostMapping("/{serviceType}")
    public ResponseEntity<Points> exchangePoints(
            @PathVariable ServiceType serviceType,
            @Valid @RequestBody RequestPointsChange request) {
        return ResponseEntity.ok(loyaltyProgramService.exchangePoints(serviceType, request));
    }

    @PutMapping("/services/{serviceType}")
    public ResponseEntity<ResponseProgramParameters> changeProgramParameters(
            @PathVariable ServiceType serviceType,
            @Valid @RequestBody RequestChangeProgramParameters request) {
        return ResponseEntity.ok(loyaltyProgramService.changeProgramParameters(serviceType, request));
    }

    @GetMapping("/services")
    public ResponseEntity<ResponseProgramParameters> getProgramParameters() {
        return ResponseEntity.ok(loyaltyProgramService.getProgramParameters());
    }

}
