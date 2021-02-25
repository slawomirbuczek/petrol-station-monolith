package com.pk.petrolstationmonolith.controllers.transactions;

import com.pk.petrolstationmonolith.dtos.transactions.TransactionDto;
import com.pk.petrolstationmonolith.models.transactions.RequestAddTransaction;
import com.pk.petrolstationmonolith.models.transactions.Transactions;
import com.pk.petrolstationmonolith.services.transactions.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final Logger logger = LoggerFactory.getLogger(TransactionController.class);
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<TransactionDto> addTransaction(@Valid @RequestBody RequestAddTransaction request) {
        logger.trace("addTransaction method invoked");
        return ResponseEntity.ok(transactionService.addTransaction(request, null));
    }

    @PostMapping("/{userId}")
    public ResponseEntity<TransactionDto> addTransactionWithUser(@PathVariable long userId,
                                                                 @Valid @RequestBody RequestAddTransaction request) {
        logger.trace("addTransactionWithUser method invoked");
        return ResponseEntity.ok(transactionService.addTransaction(request, userId));
    }

    @GetMapping
    public ResponseEntity<Transactions> getTransactions(Principal principal) {
        logger.trace("getTransactions method invoked");
        return ResponseEntity.ok(transactionService.getTransactions(Long.parseLong(principal.getName())));
    }


}
