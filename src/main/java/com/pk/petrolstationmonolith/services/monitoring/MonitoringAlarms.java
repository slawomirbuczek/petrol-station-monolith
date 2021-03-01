package com.pk.petrolstationmonolith.services.monitoring;

import com.pk.petrolstationmonolith.entities.monitoring.Parameter;
import com.pk.petrolstationmonolith.enums.ServiceType;
import com.pk.petrolstationmonolith.enums.monitoring.AlarmType;
import com.pk.petrolstationmonolith.properties.monitoring.MonitoringProperties;
import com.pk.petrolstationmonolith.repositories.monitoring.ParameterRepository;
import com.pk.petrolstationmonolith.services.mail.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MonitoringAlarms {

    private final ParameterRepository parameterRepository;
    private final MonitoringProperties monitoringProperties;
    private final MailService mailService;
    private final Logger logger = LoggerFactory.getLogger(MonitoringAlarms.class);

    public MonitoringAlarms(ParameterRepository parameterRepository,
                            MonitoringProperties monitoringProperties, MailService mailService) {
        this.parameterRepository = parameterRepository;
        this.monitoringProperties = monitoringProperties;
        this.mailService = mailService;
    }

    public void checkMonitoringAlarmState() {
        Parameter parameter = parameterRepository.findTopByOrderByDateTimeDesc();

        if (parameter.getE95Level() <= monitoringProperties.getLowLevelE95()) {
            sendAlarmEmail(ServiceType.E95, AlarmType.LOW_LEVEL, parameter.getE95Level(), parameter.getDateTime());
        }
        if (parameter.getE98Level() <= monitoringProperties.getLowLevelE98()) {
            sendAlarmEmail(ServiceType.E98, AlarmType.LOW_LEVEL, parameter.getE98Level(), parameter.getDateTime());
        }
        if (parameter.getOnLevel() <= monitoringProperties.getLowLevelOn()) {
            sendAlarmEmail(ServiceType.ON, AlarmType.LOW_LEVEL, parameter.getOnLevel(), parameter.getDateTime());
        }
        if (parameter.getLpgLevel() <= monitoringProperties.getLowLevelLpg()) {
            sendAlarmEmail(ServiceType.LPG, AlarmType.LOW_LEVEL, parameter.getLpgLevel(), parameter.getDateTime());
        }

        if (parameter.getE95Pressure() >= monitoringProperties.getHightPressureE95()) {
            sendAlarmEmail(ServiceType.E95, AlarmType.HIGHT_PRESSURE, parameter.getE95Pressure(), parameter.getDateTime());
        }
        if (parameter.getE98Pressure() >= monitoringProperties.getHightPressureE98()) {
            sendAlarmEmail(ServiceType.E98, AlarmType.HIGHT_PRESSURE, parameter.getE98Pressure(), parameter.getDateTime());
        }
        if (parameter.getOnPressure() >= monitoringProperties.getHightPressureOn()) {
            sendAlarmEmail(ServiceType.ON, AlarmType.HIGHT_PRESSURE, parameter.getOnPressure(), parameter.getDateTime());
        }
        if (parameter.getLpgPressure() >= monitoringProperties.getHightPressureLpg()) {
            sendAlarmEmail(ServiceType.LPG, AlarmType.HIGHT_PRESSURE, parameter.getLpgPressure(), parameter.getDateTime());
        }

        if (parameter.getE95Temperature() >= monitoringProperties.getHighTemperatureE95()) {
            sendAlarmEmail(ServiceType.E95, AlarmType.HIGH_TEMPERATURE, parameter.getE95Temperature(), parameter.getDateTime());
        }
        if (parameter.getE98Temperature() >= monitoringProperties.getHighTemperatureE98()) {
            sendAlarmEmail(ServiceType.E98, AlarmType.HIGH_TEMPERATURE, parameter.getE98Temperature(), parameter.getDateTime());
        }
        if (parameter.getOnTemperature() >= monitoringProperties.getHighTemperatureOn()) {
            sendAlarmEmail(ServiceType.ON, AlarmType.HIGH_TEMPERATURE, parameter.getOnTemperature(), parameter.getDateTime());
        }
        if (parameter.getLpgTemperature() >= monitoringProperties.getHighTemperatureLpg()) {
            sendAlarmEmail(ServiceType.LPG, AlarmType.HIGH_TEMPERATURE, parameter.getLpgTemperature(), parameter.getDateTime());
        }

    }

    public void sendAlarmEmail(ServiceType serviceType, AlarmType alarmType, int number, LocalDateTime dateTime) {
        logger.trace("Sending Alarm Email, serviceType: " + serviceType + " alarmType: " +
                alarmType + " number: " + number + " dateTime: " + dateTime);
        String mail = "eal08544@zwoho.com"; //paste ur temp email here
        mailService.sendMonitoringAlarmMail(mail, serviceType, alarmType, number, dateTime);
    }

}
