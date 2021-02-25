package com.pk.petrolstationmonolith.exceptions.account;

public class IndividualNotFoundException extends RuntimeException {

    public IndividualNotFoundException(long userId) {
        super("Individual with user id: " + userId + " not found");
    }
}
