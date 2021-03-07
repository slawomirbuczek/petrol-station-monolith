package com.pk.petrolstationmonolith.exceptions.account.address;

public class AddressNotFoundException extends RuntimeException {

    public AddressNotFoundException(long customerId) {
        super("Address with customer id: " + customerId + " not found");
    }

}
