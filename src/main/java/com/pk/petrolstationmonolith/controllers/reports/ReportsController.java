package com.pk.petrolstationmonolith.controllers.reports;

import com.pk.petrolstationmonolith.models.monitoring.ResponseParameters;
import com.pk.petrolstationmonolith.services.raports.ReportService;
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

    private final ReportService reportService;


    public ReportsController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/monitoring/{year}/{month}")
    public ResponseEntity<ResponseParameters> getMonitoringMonthlyReport(@PathVariable @Min(2000) @Max(2050) int year,
                                                                         @PathVariable @Min(1) @Max(12) int month) {
        return ResponseEntity.ok(reportService.getMonitoringMonthlyReport(year, month));
    }

}
