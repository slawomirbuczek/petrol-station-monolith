package com.pk.petrolstationmonolith.controllers.pricelist;

import com.pk.petrolstationmonolith.entities.pricelist.PetrolStationService;
import com.pk.petrolstationmonolith.models.pricelist.ResponsePriceList;
import com.pk.petrolstationmonolith.services.pricelist.PriceListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/pricelist")
public class PriceListController {

    private final PriceListService priceListService;

    public PriceListController(PriceListService priceListService) {
        this.priceListService = priceListService;
    }

    @GetMapping
    public ResponseEntity<ResponsePriceList> getPriceList() {
        return ResponseEntity.ok(priceListService.getPriceList());
    }

    @PutMapping
    public ResponseEntity<PetrolStationService> updateService(@Valid @RequestBody PetrolStationService service) {
        return ResponseEntity.ok(priceListService.updateService(service));
    }

}
