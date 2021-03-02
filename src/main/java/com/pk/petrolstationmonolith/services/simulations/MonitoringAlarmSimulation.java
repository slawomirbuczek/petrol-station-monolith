package com.pk.petrolstationmonolith.services.simulations;

import com.pk.petrolstationmonolith.models.simulations.RequestMonitoringAlarm;
import com.pk.petrolstationmonolith.services.mail.MailService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class MonitoringAlarmSimulation {

    private final MailService mailService;
    private final Logger logger = LoggerFactory.getLogger(MonitoringAlarmSimulation.class);

    public void simulateMonitoringAlarm(RequestMonitoringAlarm request) {
        logger.trace("Sending simulated monitoring alarm.");
        mailService.sendMonitoringAlarmMail(request.getEmail(), request.getServiceType(),
                request.getAlarmType(), request.getTankNumber(), request.getValue(), LocalDateTime.now());
    }

}
