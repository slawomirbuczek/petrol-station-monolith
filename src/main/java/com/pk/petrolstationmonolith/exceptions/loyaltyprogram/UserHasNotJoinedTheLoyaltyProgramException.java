package com.pk.petrolstationmonolith.exceptions.loyaltyprogram;

public class UserHasNotJoinedTheLoyaltyProgramException extends RuntimeException {
    public UserHasNotJoinedTheLoyaltyProgramException(long id) {
        super("User with id: " + id + " has not joined the loyalty program");
    }
}
