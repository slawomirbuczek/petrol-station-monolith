package com.pk.petrolstationmonolith.models.pricelist;

import com.pk.petrolstationmonolith.entities.pricelist.PetrolStationService;

import java.util.List;

public class PriceList {

    private List<PetrolStationService> priceList;


    public PriceList() {
    }

    public PriceList(List<PetrolStationService> priceList) {
        this.priceList = priceList;
    }

    public List<PetrolStationService> getPriceList() {
        return priceList;
    }

    public void setPriceList(List<PetrolStationService> priceList) {
        this.priceList = priceList;
    }
}
