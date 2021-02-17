package com.pk.petrolstationmonolith.entities.pricelist;

import com.pk.petrolstationmonolith.enums.pricelist.ServiceType;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Table(name = "/price_list")
public class PetrolStationService {

    @Id
    @Enumerated(EnumType.STRING)
    private ServiceType serviceType;

    @Min(0)
    private float price;

    public PetrolStationService() {
    }

    public PetrolStationService(ServiceType serviceType, float price) {
        this.serviceType = serviceType;
        this.price = price;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

}
