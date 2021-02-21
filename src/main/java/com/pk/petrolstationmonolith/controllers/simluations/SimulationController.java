package com.pk.petrolstationmonolith.controllers.simluations;

import com.pk.petrolstationmonolith.models.simulations.RequestMonitoringSimulation;
import com.pk.petrolstationmonolith.services.simulations.MonitoringAlarmSimulation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/simulations")
public class SimulationController {

    private final MonitoringAlarmSimulation monitoringAlarmSimulation;

    public SimulationController(MonitoringAlarmSimulation monitoringAlarmSimulation) {
        this.monitoringAlarmSimulation = monitoringAlarmSimulation;
    }

    @PostMapping("/monitoring")
    @ResponseStatus(HttpStatus.OK)
    public void simulateMonitoringAlarm(@RequestBody RequestMonitoringSimulation request) {
        monitoringAlarmSimulation.simulateMonitoringAlarm(request);
    }

}
