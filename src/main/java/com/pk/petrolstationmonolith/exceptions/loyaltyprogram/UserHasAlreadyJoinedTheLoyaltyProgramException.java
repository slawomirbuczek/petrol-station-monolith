package com.pk.petrolstationmonolith.exceptions.loyaltyprogram;

public class UserHasAlreadyJoinedTheLoyaltyProgramException extends RuntimeException {
    public UserHasAlreadyJoinedTheLoyaltyProgramException(long id) {
        super("User with id: " + id + " has already joined the loyalty program");
    }
}
