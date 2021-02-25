package com.pk.petrolstationmonolith.exceptions.transactions;

public class InvalidTransactionIdException extends RuntimeException {
    public InvalidTransactionIdException(long id) {
        super("Transaction with id: " + id + " not found");
    }
}
