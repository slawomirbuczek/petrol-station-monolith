package com.pk.petrolstationmonolith.exceptions.supplies;

public class SupplyNotFoundException extends RuntimeException {

    public SupplyNotFoundException(long id) {
        super("Supply with id: " + id + " not found");
    }

}
