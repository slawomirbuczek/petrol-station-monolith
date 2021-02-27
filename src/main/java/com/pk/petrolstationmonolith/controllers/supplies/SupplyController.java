package com.pk.petrolstationmonolith.controllers.supplies;

import com.pk.petrolstationmonolith.dtos.supplies.SupplyDto;
import com.pk.petrolstationmonolith.entities.supplies.Supply;
import com.pk.petrolstationmonolith.models.supplies.Supplies;
import com.pk.petrolstationmonolith.services.supplies.SupplyService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/supplies")
@PreAuthorize("hasRole('OWNER')")
public class SupplyController {

    private final SupplyService supplyService;

    public SupplyController(SupplyService supplyService) {
        this.supplyService = supplyService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Supply> getSupplyById(@PathVariable long id) {
        return ResponseEntity.ok(supplyService.getSupplyById(id));
    }

    @GetMapping
    public ResponseEntity<Supplies> getSuppliesByDateBetween(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> from,
                                                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> to) {
        return ResponseEntity.ok(supplyService.getSuppliesByDateBetween(from, to));
    }

    @PostMapping
    public ResponseEntity<Supply> addSuply(@Valid @RequestBody SupplyDto supplyDto) {
        return ResponseEntity.ok(supplyService.addSupply(supplyDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Supply> updateSupply(@PathVariable long id, @Valid @RequestBody SupplyDto supplyDto) {
        return ResponseEntity.ok(supplyService.updateSupply(id, supplyDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Supply> deleteSupply(@PathVariable long id) {
        return ResponseEntity.ok(supplyService.deleteSupply(id));
    }

}
