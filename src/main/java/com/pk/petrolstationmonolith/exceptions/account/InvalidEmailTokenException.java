package com.pk.petrolstationmonolith.exceptions.account;

public class InvalidEmailTokenException extends RuntimeException {
    public InvalidEmailTokenException() {
        super("Invalid email token");
    }
}
