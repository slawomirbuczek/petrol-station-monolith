package com.pk.petrolstationmonolith.exceptions.account;

public class EmailNotFoundException extends RuntimeException{
    public EmailNotFoundException(String email) {
        super("Customer with email: " + email + " not found.");
    }
}
