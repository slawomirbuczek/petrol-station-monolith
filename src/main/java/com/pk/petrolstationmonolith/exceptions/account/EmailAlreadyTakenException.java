package com.pk.petrolstationmonolith.exceptions.account;

public class EmailAlreadyTakenException extends RuntimeException {
    public EmailAlreadyTakenException(String email) {
        super("Email: " + email + " is already taken");
    }
}
