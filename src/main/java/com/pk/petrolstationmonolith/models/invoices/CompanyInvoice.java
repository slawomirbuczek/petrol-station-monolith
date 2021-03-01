package com.pk.petrolstationmonolith.models.invoices;

import com.pk.petrolstationmonolith.dtos.account.AddressDto;
import com.pk.petrolstationmonolith.dtos.account.CompanyDto;
import com.pk.petrolstationmonolith.dtos.transactions.TransactionDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyInvoice {

    private CompanyDto companyDto;

    private AddressDto addressDto;

    private TransactionDto transactionDto;

}
