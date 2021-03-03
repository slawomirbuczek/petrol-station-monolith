package com.pk.petrolstationmonolith.services.pricelist;

import com.pk.petrolstationmonolith.entities.pricelist.PetrolStationService;
import com.pk.petrolstationmonolith.models.pricelist.ResponsePriceList;
import com.pk.petrolstationmonolith.repositories.pricelist.ServiceRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class PriceListService {

    private final ServiceRepository serviceRepository;

    public ResponsePriceList getPriceList() {
        log.trace("Getting price list");
        return new ResponsePriceList(serviceRepository.findAll());
    }

    public void addService(PetrolStationService service) {
        log.trace("Adding service " + service.toString());
        serviceRepository.save(service);
    }

    public PetrolStationService updateService(PetrolStationService service) {
        log.trace("Updating service " + service.toString());
        return serviceRepository.save(service);
    }

}
