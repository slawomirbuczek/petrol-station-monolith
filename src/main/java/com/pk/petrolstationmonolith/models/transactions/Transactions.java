package com.pk.petrolstationmonolith.models.transactions;

import com.pk.petrolstationmonolith.dtos.transactions.TransactionDto;

import java.util.List;

public class Transactions {

    private final List<TransactionDto> transactions;

    public Transactions(List<TransactionDto> transactions) {
        this.transactions = transactions;
    }

    public List<TransactionDto> getTransactions() {
        return transactions;
    }
}
