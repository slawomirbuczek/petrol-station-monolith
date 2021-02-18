package com.pk.petrolstationmonolith.exceptions.account;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException() {
        super("Invalid password");
    }
}
