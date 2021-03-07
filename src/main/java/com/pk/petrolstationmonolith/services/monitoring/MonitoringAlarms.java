package com.pk.petrolstationmonolith.services.monitoring;

import com.pk.petrolstationmonolith.entities.monitoring.Parameters;
import com.pk.petrolstationmonolith.enums.AlarmType;
import com.pk.petrolstationmonolith.enums.ServiceType;
import com.pk.petrolstationmonolith.properties.monitoring.ParameterProperites;
import com.pk.petrolstationmonolith.services.account.CustomerService;
import com.pk.petrolstationmonolith.services.mail.MailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class MonitoringAlarms {

    private final ParameterProperites e95Properties;
    private final ParameterProperites e98Properties;
    private final ParameterProperites onProperties;
    private final ParameterProperites lpgProperties;
    private final MailService mailService;
    private final CustomerService customerService;

    public void checkMonitoringAlarmState(Parameters parameter) {
        log.trace("Checking monitoring alarm state");

        switch (parameter.getFuelType()) {
            case E95:
                checkPropertyAlarmState(parameter, e95Properties, ServiceType.E95);
                break;
            case E98:
                checkPropertyAlarmState(parameter, e98Properties, ServiceType.E98);
                break;
            case ON:
                checkPropertyAlarmState(parameter, onProperties, ServiceType.ON);
                break;
            case LPG:
                checkPropertyAlarmState(parameter, lpgProperties, ServiceType.LPG);
                break;
        }

    }

    private void checkPropertyAlarmState(Parameters parameter, ParameterProperites properites, ServiceType serviceType) {
        if (parameter.getLevel() < properites.getMinLevel()) {
            sendAlarmEmail(parameter, AlarmType.LOW_LEVEL, parameter.getLevel(), serviceType);
        }
        if (parameter.getTemperature() > properites.getMaxTemperature()) {
            sendAlarmEmail(parameter, AlarmType.HIGH_TEMPERATURE, parameter.getTemperature(), serviceType);
        }
        if (parameter.getPressure() > properites.getHighPressure()) {
            sendAlarmEmail(parameter, AlarmType.HIGHT_PRESSURE, parameter.getPressure(), serviceType);
        }
    }

    private void sendAlarmEmail(Parameters parameter, AlarmType alarmType, Float value, ServiceType serviceType) {
        log.trace("Sending Alarm Email, serviceType: " + serviceType + " alarmType: " +
                alarmType + " value: " + value + " dateTime: " + parameter.getDateTime());

        String email = customerService.getOwnerEmail();
        mailService.sendMonitoringAlarmMail(email, serviceType, alarmType, parameter.getTankNumber(),
                value, parameter.getDateTime());
    }

}
