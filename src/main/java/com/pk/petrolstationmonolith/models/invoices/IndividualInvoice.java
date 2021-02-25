package com.pk.petrolstationmonolith.models.invoices;

import com.pk.petrolstationmonolith.dtos.account.AddressDto;
import com.pk.petrolstationmonolith.dtos.account.IndividualDto;
import com.pk.petrolstationmonolith.dtos.transactions.TransactionDto;

public class IndividualInvoice {

    private IndividualDto individualDto;

    private AddressDto addressDto;

    private TransactionDto transactionDto;

    public IndividualInvoice() {
    }

    public IndividualInvoice(IndividualDto individualDto, AddressDto addressDto, TransactionDto transactionDto) {
        this.individualDto = individualDto;
        this.addressDto = addressDto;
        this.transactionDto = transactionDto;
    }

    public IndividualDto getIndividualDto() {
        return individualDto;
    }

    public void setIndividualDto(IndividualDto individualDto) {
        this.individualDto = individualDto;
    }

    public AddressDto getAddressDto() {
        return addressDto;
    }

    public void setAddressDto(AddressDto addressDto) {
        this.addressDto = addressDto;
    }

    public TransactionDto getTransactionDto() {
        return transactionDto;
    }

    public void setTransactionDto(TransactionDto transactionDto) {
        this.transactionDto = transactionDto;
    }

}
