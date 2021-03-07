package com.pk.petrolstationmonolith.controllers.transactions;

import com.pk.petrolstationmonolith.dtos.transactions.TransactionDto;
import com.pk.petrolstationmonolith.models.transactions.RequestAddTransaction;
import com.pk.petrolstationmonolith.models.transactions.Transactions;
import com.pk.petrolstationmonolith.services.transactions.TransactionService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/transactions")
@AllArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    @PreAuthorize("hasAnyRole('CASHIER','ADMIN','OWNER')")
    public ResponseEntity<TransactionDto> addTransaction(@Valid @RequestBody RequestAddTransaction request) {
        return ResponseEntity.ok(transactionService.addTransaction(request, null));
    }

    @PostMapping("/{customerId}")
    @PreAuthorize("hasAnyRole('CASHIER','ADMIN','OWNER')")
    public ResponseEntity<TransactionDto> addTransactionWithCustomer(@PathVariable long customerId,
                                                                 @Valid @RequestBody RequestAddTransaction request) {
        return ResponseEntity.ok(transactionService.addTransaction(request, customerId));
    }

    @GetMapping
    public ResponseEntity<Transactions> getTransactions(Principal principal) {
        return ResponseEntity.ok(transactionService.getTransactions(Long.parseLong(principal.getName())));
    }


}
