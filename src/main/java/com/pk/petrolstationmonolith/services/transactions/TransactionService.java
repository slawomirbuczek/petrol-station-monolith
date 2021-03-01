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
        report.setE95Number(transactionRepository.sumNumberByServiceTypeAndDateTimeBetween(ServiceType.E95, from, to).orElse(0L));
        report.setE95Profit(transactionRepository.sumProfitByServiceTypeAndDateTimeBetween(ServiceType.E95, from, to).orElse(0d));
        report.setE98Number(transactionRepository.sumNumberByServiceTypeAndDateTimeBetween(ServiceType.E98, from, to).orElse(0L));
        report.setE98Profit(transactionRepository.sumProfitByServiceTypeAndDateTimeBetween(ServiceType.E98, from, to).orElse(0d));
        report.setOnNumber(transactionRepository.sumNumberByServiceTypeAndDateTimeBetween(ServiceType.ON, from, to).orElse(0L));
        report.setOnProfit(transactionRepository.sumProfitByServiceTypeAndDateTimeBetween(ServiceType.ON, from, to).orElse(0d));
        report.setLpgNumber(transactionRepository.sumNumberByServiceTypeAndDateTimeBetween(ServiceType.LPG, from, to).orElse(0L));
        report.setLpgProfit(transactionRepository.sumProfitByServiceTypeAndDateTimeBetween(ServiceType.LPG, from, to).orElse(0d));
        report.setWashingStandardNumber(transactionRepository.sumNumberByServiceTypeAndDateTimeBetween(ServiceType.WASHING_STANDARD, from, to).orElse(0L));
        report.setWashingStandardProfit(transactionRepository.sumProfitByServiceTypeAndDateTimeBetween(ServiceType.WASHING_STANDARD, from, to).orElse(0d));
        report.setWashingWaxingNumber(transactionRepository.sumNumberByServiceTypeAndDateTimeBetween(ServiceType.WASHING_WAXING, from, to).orElse(0L));
        report.setWashingWaxingProfit(transactionRepository.sumProfitByServiceTypeAndDateTimeBetween(ServiceType.WASHING_WAXING, from, to).orElse(0d));
        return report;
    }

    private TransactionDto mapTransactionToDto(Transaction transaction) {
        return mapper.map(transaction, TransactionDto.class);
    }

}
