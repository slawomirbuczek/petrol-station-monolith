package com.pk.petrolstationmonolith.exceptions.account.address;

public class AddressNotFoundException extends RuntimeException {

    public AddressNotFoundException(long userId) {
        super("Address with user id: " + userId + " not found");
    }

}
