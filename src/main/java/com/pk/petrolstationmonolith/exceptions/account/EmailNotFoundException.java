package com.pk.petrolstationmonolith.exceptions.account;

public class EmailNotFoundException extends RuntimeException{
    public EmailNotFoundException(String email) {
        super("User with email: " + email + " not found.");
    }
}
