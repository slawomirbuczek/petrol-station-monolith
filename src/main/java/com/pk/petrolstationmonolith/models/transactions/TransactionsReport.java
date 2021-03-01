package com.pk.petrolstationmonolith.models.transactions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionsReport {

    private long e95Number;
    private double e95Profit;
    private long e98Number;
    private double e98Profit;
    private long onNumber;
    private double onProfit;
    private long lpgNumber;
    private double lpgProfit;
    private long washingStandardNumber;
    private double washingStandardProfit;
    private long washingWaxingNumber;
    private double washingWaxingProfit;

}
