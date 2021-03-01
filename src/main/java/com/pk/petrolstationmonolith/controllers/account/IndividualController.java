package com.pk.petrolstationmonolith.controllers.account;

import com.pk.petrolstationmonolith.dtos.account.IndividualDto;
import com.pk.petrolstationmonolith.services.account.IndividualService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/account/individuals")
@AllArgsConstructor
public class IndividualController {

    private final IndividualService individualService;

    @GetMapping
    public ResponseEntity<IndividualDto> getIndividual(Principal principal) {
        return ResponseEntity.ok(individualService.getIndividualDto(Long.parseLong(principal.getName())));
    }

    @GetMapping("/users/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN','OWNER')")
    public ResponseEntity<IndividualDto> getIndividualDtoByUserId(@PathVariable long userId) {
        return ResponseEntity.ok(individualService.getIndividualDto(userId));
    }

    @PutMapping
    public ResponseEntity<IndividualDto> updateIndividual(Principal principal,
                                                    @Valid @RequestBody IndividualDto individualDto) {
        return ResponseEntity.ok(individualService.updateIndividual(Long.parseLong(principal.getName()), individualDto));
    }

    @PutMapping("/users/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN','OWNER')")
    public ResponseEntity<IndividualDto> updateIndividualByUserId(@PathVariable long userId,
                                                            @Valid @RequestBody IndividualDto individualDto) {
        return ResponseEntity.ok(individualService.updateIndividual(userId, individualDto));
    }

}
