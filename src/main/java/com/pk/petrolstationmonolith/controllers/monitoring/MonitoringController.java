package com.pk.petrolstationmonolith.controllers.monitoring;

import com.pk.petrolstationmonolith.controllers.account.AccountController;
import com.pk.petrolstationmonolith.dtos.monitoring.ParameterDto;
import com.pk.petrolstationmonolith.models.ResponseMessage;
import com.pk.petrolstationmonolith.models.monitoring.ResponseParameters;
import com.pk.petrolstationmonolith.models.monitoring.RequestChangeInterval;
import com.pk.petrolstationmonolith.models.monitoring.RequestParametersBetweenDates;
import com.pk.petrolstationmonolith.services.monitoring.MonitoringService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/monitoring")
public class MonitoringController {

    private final Logger logger = LoggerFactory.getLogger(MonitoringController.class);
    private final MonitoringService monitoringService;

    public MonitoringController(MonitoringService monitoringService) {
        this.monitoringService = monitoringService;
    }

    @GetMapping("/parameters")
    public ResponseEntity<ParameterDto> getCurrentParameters() {
        logger.trace("getCurrentParameters method invoked");
        return ResponseEntity.ok(monitoringService.getCurrentParameters());
    }

    @PostMapping("/parameters")
    public ResponseEntity<ResponseParameters> getParametersBetweenDates(@Valid @RequestBody RequestParametersBetweenDates request) {
        logger.trace("getParametersBetweenDates method invoked");
        return ResponseEntity.ok(monitoringService.getParametersBetweenDates(request));
    }

    @PutMapping
    public ResponseEntity<ResponseMessage> changeMonitoringInterval(@Valid @RequestBody RequestChangeInterval request) {
        logger.trace("changeMonitoringInterval method invoked");
        return ResponseEntity.ok(monitoringService.changeMonitoringInterval(request));
    }

}
