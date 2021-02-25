package com.pk.petrolstationmonolith.exceptions.transactions;

public class TransactionNotAssociatedWithUserException extends RuntimeException {

    public TransactionNotAssociatedWithUserException(long id, long userId) {
        super("Transaction with id: " + id + " not associated with user with id: " + userId);
    }
}
