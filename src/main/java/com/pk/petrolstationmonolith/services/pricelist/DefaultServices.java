package com.pk.petrolstationmonolith.services.pricelist;


import com.pk.petrolstationmonolith.entities.pricelist.PetrolStationService;
import com.pk.petrolstationmonolith.enums.ServiceType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@AllArgsConstructor
@Slf4j
public class DefaultServices {

    private final PriceListService priceListService;

    @PostConstruct
    private void addDefaultServices() {
        log.trace("Adding defalut services");
        priceListService.addService(new PetrolStationService(ServiceType.E95, 5.59f));
        priceListService.addService(new PetrolStationService(ServiceType.E98, 5.99f));
        priceListService.addService(new PetrolStationService(ServiceType.ON, 5.59f));
        priceListService.addService(new PetrolStationService(ServiceType.LPG, 2.89f));
        priceListService.addService(new PetrolStationService(ServiceType.WASHING_STANDARD, 19.99f));
        priceListService.addService(new PetrolStationService(ServiceType.WASHING_WAXING, 34.99f));
    }

}
