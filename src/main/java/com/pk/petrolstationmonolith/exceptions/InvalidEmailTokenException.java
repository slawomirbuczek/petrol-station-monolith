package com.pk.petrolstationmonolith.exceptions;

public class InvalidEmailTokenException extends RuntimeException {
    public InvalidEmailTokenException() {
        super("Invalid email token");
    }
}
