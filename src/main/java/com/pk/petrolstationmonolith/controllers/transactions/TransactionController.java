package com.pk.petrolstationmonolith.controllers.transactions;

import com.pk.petrolstationmonolith.dtos.transactions.TransactionDto;
import com.pk.petrolstationmonolith.models.transactions.Transactions;
import com.pk.petrolstationmonolith.services.transactions.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<TransactionDto> addTransaction(@Valid @RequestBody TransactionDto transactionDto) {
        return ResponseEntity.ok(transactionService.addTransaction(transactionDto, null));
    }

    @PostMapping("/{userId}")
    public ResponseEntity<TransactionDto> addTransactionWithUser(@PathVariable long userId,
                                                                 @Valid @RequestBody TransactionDto transactionDto) {
        return ResponseEntity.ok(transactionService.addTransaction(transactionDto, userId));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Transactions> getTransactionsByUser(@PathVariable long userId) {
        return ResponseEntity.ok(transactionService.getTransactions(userId));
    }

    @GetMapping
    public ResponseEntity<Transactions> getTransactions(Principal principal) {
        return ResponseEntity.ok(transactionService.getTransactions(Long.parseLong(principal.getName())));
    }

}
