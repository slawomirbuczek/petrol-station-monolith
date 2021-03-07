package com.pk.petrolstationmonolith.exceptions.loyaltyprogram;

public class NotEnoughLoyaltyProgramPointsException extends RuntimeException{
    public NotEnoughLoyaltyProgramPointsException(Long id) {
        super("Customer with id: " + id + " has not enaugh loyalty program points");
    }
}
