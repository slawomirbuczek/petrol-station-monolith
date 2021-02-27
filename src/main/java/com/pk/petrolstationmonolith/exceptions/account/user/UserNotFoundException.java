package com.pk.petrolstationmonolith.exceptions.account.user;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(long id) {
        super("User with id: " + id + " not found");
    }

    public UserNotFoundException(String id) {
        super("User with id: " + id + " not found");
    }

}
