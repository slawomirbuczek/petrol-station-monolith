package com.pk.petrolstationmonolith.controllers.pricelist;

import com.pk.petrolstationmonolith.entities.pricelist.PetrolStationService;
import com.pk.petrolstationmonolith.models.pricelist.ResponsePriceList;
import com.pk.petrolstationmonolith.services.pricelist.PriceListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/pricelist")
public class PriceListController {

    private final Logger logger = LoggerFactory.getLogger(PriceListController.class);
    private final PriceListService priceListService;

    public PriceListController(PriceListService priceListService) {
        this.priceListService = priceListService;
    }

    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<ResponsePriceList> getPriceList() {
        logger.trace("getPriceList method invoked");
        return ResponseEntity.ok(priceListService.getPriceList());
    }

    @PutMapping
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<PetrolStationService> updateService(@Valid @RequestBody PetrolStationService service) {
        logger.trace("updateService method invoked");
        return ResponseEntity.ok(priceListService.updateService(service));
    }

}
