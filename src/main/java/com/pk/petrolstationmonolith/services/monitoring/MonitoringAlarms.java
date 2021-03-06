package com.pk.petrolstationmonolith.services.monitoring;

import com.pk.petrolstationmonolith.entities.monitoring.Monitoring;
import com.pk.petrolstationmonolith.enums.ServiceType;
import com.pk.petrolstationmonolith.enums.AlarmType;
import com.pk.petrolstationmonolith.properties.monitoring.MonitoringProperties;
import com.pk.petrolstationmonolith.repositories.monitoring.ParameterRepository;
import com.pk.petrolstationmonolith.services.mail.MailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class MonitoringAlarms {

    private final ParameterRepository parameterRepository;
    private final MonitoringProperties monitoringProperties;
    private final MailService mailService;
    private final Logger logger = LoggerFactory.getLogger(MonitoringAlarms.class);

    public void checkMonitoringAlarmState() {
        log.trace("Checking monitoring alarm state");
        Monitoring monitoring = parameterRepository.findTopByOrderByDateTimeDesc();

        if (monitoring.getE95Level() <= monitoringProperties.getLowLevelE95()) {
            sendAlarmEmail(ServiceType.E95, AlarmType.LOW_LEVEL, monitoring.getE95Level(), monitoring.getDateTime());
        }
        if (monitoring.getE98Level() <= monitoringProperties.getLowLevelE98()) {
            sendAlarmEmail(ServiceType.E98, AlarmType.LOW_LEVEL, monitoring.getE98Level(), monitoring.getDateTime());
        }
        if (monitoring.getOnLevel() <= monitoringProperties.getLowLevelOn()) {
            sendAlarmEmail(ServiceType.ON, AlarmType.LOW_LEVEL, monitoring.getOnLevel(), monitoring.getDateTime());
        }
        if (monitoring.getLpgLevel() <= monitoringProperties.getLowLevelLpg()) {
            sendAlarmEmail(ServiceType.LPG, AlarmType.LOW_LEVEL, monitoring.getLpgLevel(), monitoring.getDateTime());
        }

        if (monitoring.getE95Pressure() >= monitoringProperties.getHightPressureE95()) {
            sendAlarmEmail(ServiceType.E95, AlarmType.HIGHT_PRESSURE, monitoring.getE95Pressure(), monitoring.getDateTime());
        }
        if (monitoring.getE98Pressure() >= monitoringProperties.getHightPressureE98()) {
            sendAlarmEmail(ServiceType.E98, AlarmType.HIGHT_PRESSURE, monitoring.getE98Pressure(), monitoring.getDateTime());
        }
        if (monitoring.getOnPressure() >= monitoringProperties.getHightPressureOn()) {
            sendAlarmEmail(ServiceType.ON, AlarmType.HIGHT_PRESSURE, monitoring.getOnPressure(), monitoring.getDateTime());
        }
        if (monitoring.getLpgPressure() >= monitoringProperties.getHightPressureLpg()) {
            sendAlarmEmail(ServiceType.LPG, AlarmType.HIGHT_PRESSURE, monitoring.getLpgPressure(), monitoring.getDateTime());
        }

        if (monitoring.getE95Temperature() >= monitoringProperties.getHighTemperatureE95()) {
            sendAlarmEmail(ServiceType.E95, AlarmType.HIGH_TEMPERATURE, monitoring.getE95Temperature(), monitoring.getDateTime());
        }
        if (monitoring.getE98Temperature() >= monitoringProperties.getHighTemperatureE98()) {
            sendAlarmEmail(ServiceType.E98, AlarmType.HIGH_TEMPERATURE, monitoring.getE98Temperature(), monitoring.getDateTime());
        }
        if (monitoring.getOnTemperature() >= monitoringProperties.getHighTemperatureOn()) {
            sendAlarmEmail(ServiceType.ON, AlarmType.HIGH_TEMPERATURE, monitoring.getOnTemperature(), monitoring.getDateTime());
        }
        if (monitoring.getLpgTemperature() >= monitoringProperties.getHighTemperatureLpg()) {
            sendAlarmEmail(ServiceType.LPG, AlarmType.HIGH_TEMPERATURE, monitoring.getLpgTemperature(), monitoring.getDateTime());
        }

    }

    public void sendAlarmEmail(ServiceType serviceType, AlarmType alarmType, int value, LocalDateTime dateTime) {
        logger.trace("Sending Alarm Email, serviceType: " + serviceType + " alarmType: " +
                alarmType + " value: " + value + " dateTime: " + dateTime);
        String mail = "eal08544@zwoho.com"; //paste ur temp email here
        mailService.sendMonitoringAlarmMail(mail, serviceType, alarmType, 1, value, dateTime);
    }

}
