package com.pk.petrolstationmonolith.services.pricelist;

import com.pk.petrolstationmonolith.entities.pricelist.PetrolStationService;
import com.pk.petrolstationmonolith.models.pricelist.ResponsePriceList;
import com.pk.petrolstationmonolith.repositories.pricelist.ServiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PriceListService {

    private final ServiceRepository serviceRepository;

    public ResponsePriceList getPriceList() {
        return new ResponsePriceList(serviceRepository.findAll());
    }

    public void addService(PetrolStationService service) {
        serviceRepository.save(service);
    }

    public PetrolStationService updateService(PetrolStationService service) {
        return serviceRepository.save(service);
    }

}
