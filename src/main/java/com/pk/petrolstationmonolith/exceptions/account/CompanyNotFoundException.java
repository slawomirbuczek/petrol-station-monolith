package com.pk.petrolstationmonolith.exceptions.account;

public class CompanyNotFoundException extends RuntimeException {

    public CompanyNotFoundException(long userId) {
        super("Company with user id: " + userId + " not found");
    }

}
