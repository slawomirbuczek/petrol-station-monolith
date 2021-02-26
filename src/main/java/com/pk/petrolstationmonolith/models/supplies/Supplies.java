package com.pk.petrolstationmonolith.models.supplies;

import com.pk.petrolstationmonolith.entities.supplies.Supply;

import java.util.List;

public class Supplies {

    private List<Supply> supplies;

    public Supplies() {
    }

    public Supplies(List<Supply> supplies) {
        this.supplies = supplies;
    }

    public List<Supply> getSupplies() {
        return supplies;
    }

    public void setSupplies(List<Supply> supplies) {
        this.supplies = supplies;
    }
}
