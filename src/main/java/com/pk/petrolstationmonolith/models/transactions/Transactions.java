package com.pk.petrolstationmonolith.models.transactions;

import com.pk.petrolstationmonolith.dtos.transactions.TransactionDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
public class Transactions {

    private final List<TransactionDto> transactions;

}
