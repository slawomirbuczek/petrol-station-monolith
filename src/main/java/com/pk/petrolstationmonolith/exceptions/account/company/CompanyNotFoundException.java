package com.pk.petrolstationmonolith.exceptions.account.company;

public class CompanyNotFoundException extends RuntimeException {

    public CompanyNotFoundException(long customerId) {
        super("Company with customer id: " + customerId + " not found");
    }

}
