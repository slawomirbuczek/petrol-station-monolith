package com.pk.petrolstationmonolith.services.simulations;

import com.pk.petrolstationmonolith.models.simulations.RequestMonitoringAlarm;
import com.pk.petrolstationmonolith.services.mail.MailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class MonitoringAlarmSimulation {

    private final MailService mailService;

    public void simulateMonitoringAlarm(RequestMonitoringAlarm request) {
        log.trace("Sending simulated monitoring alarm " + request.toString());
        mailService.sendMonitoringAlarmMail(request.getEmail(), request.getServiceType(),
                request.getAlarmType(), request.getTankNumber(), request.getValue(), LocalDateTime.now());
    }

}
