package com.pk.petrolstationmonolith.models.invoices;

import com.pk.petrolstationmonolith.dtos.account.AddressDto;
import com.pk.petrolstationmonolith.dtos.account.CompanyDto;
import com.pk.petrolstationmonolith.dtos.transactions.TransactionDto;

public class CompanyInvoice {

    private CompanyDto companyDto;

    private AddressDto addressDto;

    private TransactionDto transactionDto;

    public CompanyInvoice() {
    }

    public CompanyInvoice(CompanyDto companyDto, AddressDto addressDto, TransactionDto transactionDto) {
        this.companyDto = companyDto;
        this.addressDto = addressDto;
        this.transactionDto = transactionDto;
    }

    public CompanyDto getCompanyDto() {
        return companyDto;
    }

    public void setCompanyDto(CompanyDto companyDto) {
        this.companyDto = companyDto;
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
