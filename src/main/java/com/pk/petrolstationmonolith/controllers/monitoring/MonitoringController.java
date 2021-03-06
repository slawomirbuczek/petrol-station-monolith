package com.pk.petrolstationmonolith.controllers.monitoring;

import com.pk.petrolstationmonolith.models.ResponseMessage;
import com.pk.petrolstationmonolith.models.monitoring.ListOfParameters;
import com.pk.petrolstationmonolith.models.monitoring.RequestChangeInterval;
import com.pk.petrolstationmonolith.models.monitoring.RequestParametersBetweenDates;
import com.pk.petrolstationmonolith.services.monitoring.MonitoringService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/monitoring")
@PreAuthorize("hasAnyRole('MONITORING','ADMIN','OWNER')")
@AllArgsConstructor
public class MonitoringController {

    private final MonitoringService monitoringService;

    @GetMapping("/parameters")
    public ResponseEntity<ListOfParameters> getCurrentParameters() {
        return ResponseEntity.ok(monitoringService.getCurrentParameters());
    }

    @PostMapping("/parameters")
    public ResponseEntity<ListOfParameters> getParametersBetweenDates(@Valid @RequestBody RequestParametersBetweenDates request) {
        return ResponseEntity.ok(monitoringService.getParametersBetweenDates(request));
    }

    @PutMapping
    public ResponseEntity<ResponseMessage> changeMonitoringInterval(@Valid @RequestBody RequestChangeInterval request) {
        return ResponseEntity.ok(monitoringService.changeMonitoringInterval(request));
    }

}
