package com.pk.petrolstationmonolith.services.invoices;

import com.pk.petrolstationmonolith.exceptions.transactions.TransactionNotAssociatedWithCustomerException;
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

    public CompanyInvoice getCompanyInvoice(long transactionId, long customerId) {
        checkIfTransactionIsAssociatedWithUser(transactionId, customerId);
        log.trace("Getting company invoice with customer id " + customerId + " and transaction id " +  transactionId);
        return new CompanyInvoice(
                companyService.getCompanyDto(customerId),
                addressService.getAddressDto(customerId),
                transactionService.getTransactionDto(transactionId)
        );
    }

    public IndividualInvoice getIndividualInvoice(long transactionId, long customerId) {
        checkIfTransactionIsAssociatedWithUser(transactionId, customerId);
        log.trace("Getting individual invoice with customer id " + customerId + " and transaction id " +  transactionId);
        return new IndividualInvoice(
                individualService.getIndividualDto(customerId),
                addressService.getAddressDto(customerId),
                transactionService.getTransactionDto(transactionId)
        );
    }

    private void checkIfTransactionIsAssociatedWithUser(long transactionId, long customerId) {
        if (transactionService.transactionAssociatedWithCustomer(transactionId, customerId)) {
            throw new TransactionNotAssociatedWithCustomerException(transactionId, customerId);
        }
    }

}
