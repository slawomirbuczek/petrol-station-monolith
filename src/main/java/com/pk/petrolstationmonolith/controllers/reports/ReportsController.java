package com.pk.petrolstationmonolith.controllers.reports;

import com.pk.petrolstationmonolith.models.monitoring.ResponseParameters;
import com.pk.petrolstationmonolith.models.transactions.TransactionsReport;
import com.pk.petrolstationmonolith.services.monitoring.MonitoringService;
import com.pk.petrolstationmonolith.services.transactions.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/reports")
@PreAuthorize("hasAnyRole('ADMIN','OWNER')")
@AllArgsConstructor
public class ReportsController {

    private final MonitoringService monitoringService;
    private final TransactionService transactionService;

    @GetMapping("/monitoring")
    public ResponseEntity<ResponseParameters> getMonitoringMonthlyReport(@RequestParam Optional<Integer> optionalYear,
                                                                         @RequestParam Optional<Integer> optionalMonth) {
        return ResponseEntity.ok(monitoringService.getMonitoringMonthlyReport(optionalYear, optionalMonth));
    }

    @GetMapping("/transactions")
    public ResponseEntity<TransactionsReport> getTransactiontsMonthlyReport(@RequestParam Optional<Integer> optionalYear,
                                                                            @RequestParam Optional<Integer> optionalMonth) {
        return ResponseEntity.ok(transactionService.getTransactionsMonthlyReport(optionalYear, optionalMonth));
    }

}
