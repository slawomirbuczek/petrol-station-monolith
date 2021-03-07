package com.pk.petrolstationmonolith.exceptions.transactions;

public class TransactionNotAssociatedWithCustomerException extends RuntimeException {

    public TransactionNotAssociatedWithCustomerException(long id, long customerId) {
        super("Transaction with id: " + id + " not associated with customer with id: " + customerId);
    }
}
