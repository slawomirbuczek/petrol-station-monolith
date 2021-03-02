package com.pk.petrolstationmonolith.services.transactions;

import com.pk.petrolstationmonolith.dtos.transactions.TransactionDto;
import com.pk.petrolstationmonolith.entities.transactions.Transaction;
import com.pk.petrolstationmonolith.enums.ServiceType;
import com.pk.petrolstationmonolith.exceptions.transactions.InvalidTransactionIdException;
import com.pk.petrolstationmonolith.models.transactions.RequestAddTransaction;
import com.pk.petrolstationmonolith.models.transactions.Transactions;
import com.pk.petrolstationmonolith.models.transactions.TransactionsReport;
import com.pk.petrolstationmonolith.repositories.transactions.TransactionRepository;
import com.pk.petrolstationmonolith.services.account.UserService;
import com.pk.petrolstationmonolith.services.loyaltyprogram.LoyaltyProgramService;
import lombok.AllArgsConstructor;
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
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final LoyaltyProgramService loyaltyProgramService;
    private final UserService userService;
    private final ModelMapper mapper;

    public TransactionDto getTransactionDto(long transactionId) {
        return mapTransactionToDto(getTransaction(transactionId));
    }

    public Transaction getTransaction(long transactionId) {
        return transactionRepository.findById(transactionId)
                .orElseThrow(() -> new InvalidTransactionIdException(transactionId));
    }

    public Transactions getTransactions(long userId) {
        return new Transactions(transactionRepository.findAllByUserId(userId).stream()
                .map(this::mapTransactionToDto).collect(Collectors.toList()));
    }

    public TransactionDto addTransaction(RequestAddTransaction request, Long userId) {
        Transaction transaction = mapper.map(request, Transaction.class);

        if (Objects.nonNull(userId)) {
            transaction.setUser(userService.getUser(userId));
            loyaltyProgramService.addProgramPointsAfterTransaction(userId, request.getServiceType(), request.getNumber());
        }

        return mapTransactionToDto(transactionRepository.save(transaction));
    }

    public boolean transactionAssociatedWithUser(long transactionId, long userId) {
        Transaction transaction = getTransaction(transactionId);
        return Objects.nonNull(transaction.getUser()) && transaction.getUser().getId() == userId;
    }

    public TransactionsReport getTransactionsMonthlyReport(Optional<Integer> optionalYear, Optional<Integer> optionalMonth) {
        int year = optionalYear.orElseGet(() -> LocalDate.now().getYear());
        int month = optionalMonth.orElseGet(() -> LocalDate.now().getMonthValue());

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

    private TransactionDto mapTransactionToDto(Transaction transaction) {
        return mapper.map(transaction, TransactionDto.class);
    }

}
