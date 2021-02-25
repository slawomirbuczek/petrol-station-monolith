package com.pk.petrolstationmonolith.services.invoices;

import com.pk.petrolstationmonolith.entities.account.Address;
import com.pk.petrolstationmonolith.entities.account.Company;
import com.pk.petrolstationmonolith.entities.account.Individual;
import com.pk.petrolstationmonolith.entities.transactions.Transaction;
import com.pk.petrolstationmonolith.exceptions.transactions.TransactionNotAssociatedWithUserException;
import com.pk.petrolstationmonolith.models.invoices.CompanyInvoice;
import com.pk.petrolstationmonolith.models.invoices.IndividualInvoice;
import com.pk.petrolstationmonolith.services.account.AddressService;
import com.pk.petrolstationmonolith.services.account.CompanyService;
import com.pk.petrolstationmonolith.services.account.IndividualService;
import com.pk.petrolstationmonolith.services.transactions.TransactionService;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService {

    private final TransactionService transactionService;
    private final CompanyService companyService;
    private final IndividualService individualService;
    private final AddressService addressService;

    public InvoiceService(TransactionService transactionService, CompanyService companyService,
                          IndividualService individualService, AddressService addressService) {
        this.transactionService = transactionService;
        this.companyService = companyService;
        this.individualService = individualService;
        this.addressService = addressService;
    }

    public CompanyInvoice getCompanyInvoice(long transactionId, long userId) {
        Transaction transaction = transactionService.getTransaction(transactionId);

        if (transaction.getUser().getId() != userId) {
            throw new TransactionNotAssociatedWithUserException(transactionId, userId);
        }

        Company company = companyService.getCompanyByUserId(userId);

        Address address = company.getAddress();

        return new CompanyInvoice(
                companyService.mapCompanyToDto(company),
                addressService.mapAddressToDto(address),
                transactionService.mapTransactionToDto(transaction)
        );
    }

    public IndividualInvoice getIndividualInvoice(long transactionId, long userId) {
        Transaction transaction = transactionService.getTransaction(transactionId);

        if (transaction.getUser().getId() != userId) {
            throw new TransactionNotAssociatedWithUserException(transactionId, userId);
        }

        Individual individual = individualService.getIndividualByUserId(userId);

        Address address = individual.getAddress();

        return new IndividualInvoice(
                individualService.mapIndividualToDto(individual),
                addressService.mapAddressToDto(address),
                transactionService.mapTransactionToDto(transaction)
        );
    }

}
