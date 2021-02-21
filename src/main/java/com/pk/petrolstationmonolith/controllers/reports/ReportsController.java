package com.pk.petrolstationmonolith.controllers.reports;

import com.pk.petrolstationmonolith.models.monitoring.ResponseParameters;
import com.pk.petrolstationmonolith.models.transactions.TransactionsReport;
import com.pk.petrolstationmonolith.services.monitoring.MonitoringService;
import com.pk.petrolstationmonolith.services.transactions.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/reports")
@Validated
public class ReportsController {

    private final MonitoringService monitoringService;
    private final TransactionService transactionService;

    public ReportsController(MonitoringService monitoringService, TransactionService transactionService) {
        this.monitoringService = monitoringService;
        this.transactionService = transactionService;
    }

    @GetMapping("/monitoring/{year}/{month}")
    public ResponseEntity<ResponseParameters> getMonitoringMonthlyReport(@PathVariable @Min(2000) @Max(2050) int year,
                                                                         @PathVariable @Min(1) @Max(12) int month) {
        return ResponseEntity.ok(monitoringService.getMonitoringMonthlyReport(year, month));
    }

    @GetMapping("/transactions/{year}/{month}")
    public ResponseEntity<TransactionsReport> getTransactiontsMonthlyReport(@PathVariable @Min(2000) @Max(2050) int year,
                                                                            @PathVariable @Min(1) @Max(12) int month) {
        return ResponseEntity.ok(transactionService.getTransactionsMonthlyReport(year, month));
    }

}
