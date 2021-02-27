package com.pk.petrolstationmonolith.controllers.reports;

import com.pk.petrolstationmonolith.models.monitoring.Parameters;
import com.pk.petrolstationmonolith.models.transactions.TransactionsReport;
import com.pk.petrolstationmonolith.services.monitoring.MonitoringService;
import com.pk.petrolstationmonolith.services.transactions.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class ReportsController {

    private final Logger logger = LoggerFactory.getLogger(ReportsController.class);
    private final MonitoringService monitoringService;
    private final TransactionService transactionService;

    public ReportsController(MonitoringService monitoringService, TransactionService transactionService) {
        this.monitoringService = monitoringService;
        this.transactionService = transactionService;
    }

    @GetMapping("/monitoring")
    public ResponseEntity<Parameters> getMonitoringMonthlyReport(@RequestParam Optional<Integer> optionalYear,
                                                                 @RequestParam Optional<Integer> optionalMonth) {
        logger.trace("getMonitoringMonthlyReport method invoked");
        return ResponseEntity.ok(monitoringService.getMonitoringMonthlyReport(optionalYear, optionalMonth));
    }

    @GetMapping("/transactions")
    public ResponseEntity<TransactionsReport> getTransactiontsMonthlyReport(@RequestParam Optional<Integer> optionalYear,
                                                                            @RequestParam Optional<Integer> optionalMonth) {
        logger.trace("getTransactiontsMonthlyReport method invoked");
        return ResponseEntity.ok(transactionService.getTransactionsMonthlyReport(optionalYear, optionalMonth));
    }

}
