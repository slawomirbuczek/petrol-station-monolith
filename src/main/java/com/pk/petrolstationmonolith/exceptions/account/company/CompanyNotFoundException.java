package com.pk.petrolstationmonolith.exceptions.account.company;

public class CompanyNotFoundException extends RuntimeException {

    public CompanyNotFoundException(long userId) {
        super("Company with user id: " + userId + " not found");
    }

}
