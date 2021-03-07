package com.pk.petrolstationmonolith.exceptions.loyaltyprogram;

public class CustomerHasNotJoinedTheLoyaltyProgramException extends RuntimeException {
    public CustomerHasNotJoinedTheLoyaltyProgramException(long id) {
        super("Customer with id: " + id + " has not joined the loyalty program");
    }
}
