package com.pk.petrolstationmonolith.models.pricelist;

import com.pk.petrolstationmonolith.entities.pricelist.PetrolStationService;

import java.util.List;

public class ResponsePriceList {

    private List<PetrolStationService> priceList;


    public ResponsePriceList() {
    }

    public ResponsePriceList(List<PetrolStationService> priceList) {
        this.priceList = priceList;
    }

    public List<PetrolStationService> getPriceList() {
        return priceList;
    }

    public void setPriceList(List<PetrolStationService> priceList) {
        this.priceList = priceList;
    }
}
