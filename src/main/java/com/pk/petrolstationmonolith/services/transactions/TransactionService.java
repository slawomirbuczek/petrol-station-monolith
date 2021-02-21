package com.pk.petrolstationmonolith.services.transactions;

import com.pk.petrolstationmonolith.dtos.transactions.TransactionDto;
import com.pk.petrolstationmonolith.entities.account.User;
import com.pk.petrolstationmonolith.entities.transactions.Transaction;
import com.pk.petrolstationmonolith.enums.pricelist.ServiceType;
import com.pk.petrolstationmonolith.models.transactions.Transactions;
import com.pk.petrolstationmonolith.models.transactions.TransactionsReport;
import com.pk.petrolstationmonolith.repositories.transactions.TransactionRepository;
import com.pk.petrolstationmonolith.services.account.UserService;
import com.pk.petrolstationmonolith.services.loyaltyprogram.LoyaltyProgramService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final LoyaltyProgramService loyaltyProgramService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public TransactionService(TransactionRepository transactionRepository, LoyaltyProgramService loyaltyProgramService, UserService userService) {
        this.transactionRepository = transactionRepository;
        this.loyaltyProgramService = loyaltyProgramService;
        this.userService = userService;
        this.modelMapper = new ModelMapper();
    }

    public TransactionDto addTransaction(TransactionDto transactionDto, Long userId) {
        Transaction transaction = modelMapper.map(transactionDto, Transaction.class);

        if (Objects.nonNull(userId)) {
            transaction.setUser(userService.getUser(userId));
            loyaltyProgramService.addProgramPointsAfterTransaction(userId, transactionDto.getServiceType(),
                    transactionDto.getNumber());
        }
        return mapTransactionToDto(transactionRepository.save(transaction));
    }

    public Transactions getTransactions(long userId) {
        User user = userService.getUser(userId);
        return new Transactions(transactionRepository.findAllByUser(user).stream()
                .map(this::mapTransactionToDto).collect(Collectors.toList()));
    }

    public TransactionsReport getTransactionsMonthlyReport(int year, int month) {
        Date from = Date.valueOf(LocalDate.of(year, month, 1));
        Date to = Date.valueOf(LocalDate.of(year, month, YearMonth.of(year, month).lengthOfMonth()));

        TransactionsReport report = new TransactionsReport();
        report.setE95Number(transactionRepository.sumNumberByServiceTypeAndDateBetween(ServiceType.E95, from, to).orElse(0L));
        report.setE95Profit(transactionRepository.sumProfitByServiceTypeAndDateBetween(ServiceType.E95, from, to).orElse(0d));
        report.setE98Number(transactionRepository.sumNumberByServiceTypeAndDateBetween(ServiceType.E98, from, to).orElse(0L));
        report.setE98Profit(transactionRepository.sumProfitByServiceTypeAndDateBetween(ServiceType.E98, from, to).orElse(0d));
        report.setOnNumber(transactionRepository.sumNumberByServiceTypeAndDateBetween(ServiceType.ON, from, to).orElse(0L));
        report.setOnProfit(transactionRepository.sumProfitByServiceTypeAndDateBetween(ServiceType.ON, from, to).orElse(0d));
        report.setLpgNumber(transactionRepository.sumNumberByServiceTypeAndDateBetween(ServiceType.LPG, from, to).orElse(0L));
        report.setLpgProfit(transactionRepository.sumProfitByServiceTypeAndDateBetween(ServiceType.LPG, from, to).orElse(0d));
        report.setWashingStandardNumber(transactionRepository.sumNumberByServiceTypeAndDateBetween(ServiceType.WASHING_STANDARD, from, to).orElse(0L));
        report.setWashingStandardProfit(transactionRepository.sumProfitByServiceTypeAndDateBetween(ServiceType.WASHING_STANDARD, from, to).orElse(0d));
        report.setWashingWaxingNumber(transactionRepository.sumNumberByServiceTypeAndDateBetween(ServiceType.WASHING_WAXING, from, to).orElse(0L));
        report.setWashingWaxingProfit(transactionRepository.sumProfitByServiceTypeAndDateBetween(ServiceType.WASHING_WAXING, from, to).orElse(0d));
        return report;
    }

    private TransactionDto mapTransactionToDto(Transaction transaction) {
        return modelMapper.map(transaction, TransactionDto.class);
    }

}
