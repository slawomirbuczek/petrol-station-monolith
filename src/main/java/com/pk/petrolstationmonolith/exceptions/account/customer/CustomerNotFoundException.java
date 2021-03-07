package com.pk.petrolstationmonolith.exceptions.account.customer;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(long id) {
        super("Customer with id: " + id + " not found");
    }

    public CustomerNotFoundException(String id) {
        super("Customer with id: " + id + " not found");
    }

}
