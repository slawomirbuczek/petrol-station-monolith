package com.pk.petrolstationmonolith.exceptions.loyaltyprogram;

public class CustomerHasAlreadyJoinedTheLoyaltyProgramException extends RuntimeException {
    public CustomerHasAlreadyJoinedTheLoyaltyProgramException(long id) {
        super("Customer with id: " + id + " has already joined the loyalty program");
    }
}
