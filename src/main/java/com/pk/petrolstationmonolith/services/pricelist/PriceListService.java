package com.pk.petrolstationmonolith.services.pricelist;


import com.pk.petrolstationmonolith.entities.pricelist.PetrolStationService;
import com.pk.petrolstationmonolith.models.pricelist.PriceList;
import com.pk.petrolstationmonolith.repositories.pricelist.ServiceRepository;
import org.springframework.stereotype.Service;


@Service
public class PriceListService {

    private final ServiceRepository serviceRepository;

    public PriceListService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public PriceList getPriceList() {
        return new PriceList(serviceRepository.findAll());
    }

    public PetrolStationService updateService(PetrolStationService service) {
        return serviceRepository.save(service);
    }

}
