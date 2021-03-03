package com.pk.petrolstationmonolith.services.invoices;

import com.pk.petrolstationmonolith.exceptions.transactions.TransactionNotAssociatedWithUserException;
import com.pk.petrolstationmonolith.models.invoices.CompanyInvoice;
import com.pk.petrolstationmonolith.models.invoices.IndividualInvoice;
import com.pk.petrolstationmonolith.services.account.AddressService;
import com.pk.petrolstationmonolith.services.account.CompanyService;
import com.pk.petrolstationmonolith.services.account.IndividualService;
import com.pk.petrolstationmonolith.services.transactions.TransactionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class InvoiceService {

    private final TransactionService transactionService;
    private final CompanyService companyService;
    private final IndividualService individualService;
    private final AddressService addressService;

    public CompanyInvoice getCompanyInvoice(long transactionId, long userId) {
        checkIfTransactionIsAssociatedWithUser(transactionId, userId);
        log.trace("Getting company invoice with user id " + userId + " and transaction id " +  transactionId);
        return new CompanyInvoice(
                companyService.getCompanyDto(userId),
                addressService.getAddressDto(userId),
                transactionService.getTransactionDto(transactionId)
        );
    }

    public IndividualInvoice getIndividualInvoice(long transactionId, long userId) {
        checkIfTransactionIsAssociatedWithUser(transactionId, userId);
        log.trace("Getting individual invoice with user id " + userId + " and transaction id " +  transactionId);
        return new IndividualInvoice(
                individualService.getIndividualDto(userId),
                addressService.getAddressDto(userId),
                transactionService.getTransactionDto(transactionId)
        );
    }

    private void checkIfTransactionIsAssociatedWithUser(long transactionId, long userId) {
        if (transactionService.transactionAssociatedWithUser(transactionId, userId)) {
            throw new TransactionNotAssociatedWithUserException(transactionId, userId);
        }
    }

}
