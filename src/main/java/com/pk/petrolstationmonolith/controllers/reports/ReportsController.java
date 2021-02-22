package com.pk.petrolstationmonolith.controllers.reports;

import com.pk.petrolstationmonolith.controllers.layaltyprogram.LoyaltyProgramController;
import com.pk.petrolstationmonolith.models.monitoring.ResponseParameters;
import com.pk.petrolstationmonolith.models.transactions.TransactionsReport;
import com.pk.petrolstationmonolith.services.monitoring.MonitoringService;
import com.pk.petrolstationmonolith.services.transactions.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger logger = LoggerFactory.getLogger(ReportsController.class);
    private final MonitoringService monitoringService;
    private final TransactionService transactionService;

    public ReportsController(MonitoringService monitoringService, TransactionService transactionService) {
        this.monitoringService = monitoringService;
        this.transactionService = transactionService;
    }

    @GetMapping("/monitoring/{year}/{month}")
    public ResponseEntity<ResponseParameters> getMonitoringMonthlyReport(@PathVariable @Min(2000) @Max(2050) int year,
                                                                         @PathVariable @Min(1) @Max(12) int month) {
        logger.trace("getMonitoringMonthlyReport method invoked");
        return ResponseEntity.ok(monitoringService.getMonitoringMonthlyReport(year, month));
    }

    @GetMapping("/transactions/{year}/{month}")
    public ResponseEntity<TransactionsReport> getTransactiontsMonthlyReport(@PathVariable @Min(2000) @Max(2050) int year,
                                                                            @PathVariable @Min(1) @Max(12) int month) {
        logger.trace("getTransactiontsMonthlyReport method invoked");
        return ResponseEntity.ok(transactionService.getTransactionsMonthlyReport(year, month));
    }

}
