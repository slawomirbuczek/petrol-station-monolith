package com.pk.petrolstationmonolith.services.pricelist;


import com.pk.petrolstationmonolith.entities.pricelist.PetrolStationService;
import com.pk.petrolstationmonolith.enums.pricelist.ServiceType;
import com.pk.petrolstationmonolith.repositories.pricelist.ServiceRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DefaultServices {

    private final ServiceRepository serviceRepository;

    public DefaultServices(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @PostConstruct
    private void addDefauldServices() {
        serviceRepository.save(new PetrolStationService(ServiceType.E95, 5.59f));
        serviceRepository.save(new PetrolStationService(ServiceType.E98, 5.99f));
        serviceRepository.save(new PetrolStationService(ServiceType.ON, 5.59f));
        serviceRepository.save(new PetrolStationService(ServiceType.LPG, 2.89f));
        serviceRepository.save(new PetrolStationService(ServiceType.WASHING_STANDARD, 19.99f));
        serviceRepository.save(new PetrolStationService(ServiceType.WASHING_WAXING, 34.99f));
    }

}
