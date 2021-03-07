package com.pk.petrolstationmonolith.services.transactions;

import com.pk.petrolstationmonolith.dtos.transactions.TransactionDto;
import com.pk.petrolstationmonolith.entities.transactions.Transactions;
import com.pk.petrolstationmonolith.enums.ServiceType;
import com.pk.petrolstationmonolith.exceptions.transactions.InvalidTransactionIdException;
import com.pk.petrolstationmonolith.models.transactions.RequestAddTransaction;
import com.pk.petrolstationmonolith.models.transactions.TransactionsReport;
import com.pk.petrolstationmonolith.repositories.transactions.TransactionRepository;
import com.pk.petrolstationmonolith.services.account.CustomerService;
import com.pk.petrolstationmonolith.services.loyaltyprogram.LoyaltyProgramService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final LoyaltyProgramService loyaltyProgramService;
    private final CustomerService customerService;
    private final ModelMapper mapper;

    public TransactionDto getTransactionDto(long transactionId) {
        log.trace("Getting transaction dto with id " + transactionId);
        return mapTransactionToDto(getTransaction(transactionId));
    }

    public Transactions getTransaction(long transactionId) {
        log.trace("Getting transaction with id " + transactionId);
        return transactionRepository.findById(transactionId)
                .orElseThrow(() -> new InvalidTransactionIdException(transactionId));
    }

    public com.pk.petrolstationmonolith.models.transactions.Transactions getTransactions(long customerId) {
        log.trace("Getting transactions for customer with id " + customerId);
        return new com.pk.petrolstationmonolith.models.transactions.Transactions(transactionRepository.findAllByCustomersId(customerId).stream()
                .map(this::mapTransactionToDto).collect(Collectors.toList()));
    }

    public TransactionDto addTransaction(RequestAddTransaction request, Long customerId) {
        Transactions transactions = mapper.map(request, Transactions.class);
        log.trace("Adding new transaction" + (Objects.nonNull(customerId) ? " for customer with id " + customerId : ""));
        if (Objects.nonNull(customerId)) {
            transactions.setCustomers(customerService.getCustomer(customerId));
            loyaltyProgramService.addProgramPointsAfterTransaction(customerId, request.getServiceType(), request.getNumber());
        }

        return mapTransactionToDto(transactionRepository.save(transactions));
    }

    public boolean transactionAssociatedWithCustomer(long transactionId, long customerId) {
        Transactions transactions = getTransaction(transactionId);
        return Objects.nonNull(transactions.getCustomers()) && transactions.getCustomers().getId() == customerId;
    }

    public TransactionsReport getTransactionsMonthlyReport(Optional<Integer> optionalYear, Optional<Integer> optionalMonth) {
        int year = optionalYear.orElseGet(() -> LocalDate.now().getYear());
        int month = optionalMonth.orElseGet(() -> LocalDate.now().getMonthValue());
        log.trace("Getting transactions monthly report for year " + year + " and month " + month);
        LocalDateTime from = LocalDate.of(year, month, 1).atStartOfDay();
        LocalDateTime to = LocalDate.of(year, month, YearMonth.of(year, month).lengthOfMonth()).plusDays(1).atStartOfDay();

        TransactionsReport report = new TransactionsReport();
        report.setE95Number(getSumNumber(from, to, ServiceType.E95));
        report.setE95Profit(getProfit(from, to, ServiceType.E95));
        report.setE98Number(getSumNumber(from, to, ServiceType.E98));
        report.setE98Profit(getProfit(from, to, ServiceType.E98));
        report.setOnNumber(getSumNumber(from, to, ServiceType.ON));
        report.setOnProfit(getProfit(from, to, ServiceType.ON));
        report.setLpgNumber(getSumNumber(from, to, ServiceType.LPG));
        report.setLpgProfit(getProfit(from, to, ServiceType.LPG));
        report.setWashingStandardNumber(getSumNumber(from, to, ServiceType.WASHING_STANDARD));
        report.setWashingStandardProfit(getProfit(from, to, ServiceType.WASHING_STANDARD));
        report.setWashingWaxingNumber(getSumNumber(from, to, ServiceType.WASHING_WAXING));
        report.setWashingWaxingProfit(getProfit(from, to, ServiceType.WASHING_WAXING));
        return report;
    }

    private Double getProfit(LocalDateTime from, LocalDateTime to, ServiceType serviceType) {
        return transactionRepository.profitByServiceTypeAndDateTimeBetween(serviceType, from, to).orElse(0d);
    }

    private Long getSumNumber(LocalDateTime from, LocalDateTime to, ServiceType serviceType) {
        return transactionRepository.sumNumberByServiceTypeAndDateTimeBetween(serviceType, from, to).orElse(0L);
    }

    private TransactionDto mapTransactionToDto(Transactions transactions) {
        return mapper.map(transactions, TransactionDto.class);
    }

}
