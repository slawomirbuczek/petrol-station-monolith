package com.pk.petrolstationmonolith.controllers.simluations;

import com.pk.petrolstationmonolith.models.simulations.RequestMonitoringAlarm;
import com.pk.petrolstationmonolith.services.simulations.MonitoringAlarmSimulation;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/simulations")
@PreAuthorize("hasRole('OWNER')")
@AllArgsConstructor
public class SimulationController {

    private final MonitoringAlarmSimulation monitoringAlarmSimulation;

    @PostMapping("/monitoring")
    @ResponseStatus(HttpStatus.OK)
    public void simulateMonitoringAlarm(@RequestBody RequestMonitoringAlarm request) {
        monitoringAlarmSimulation.simulateMonitoringAlarm(request);
    }

}
