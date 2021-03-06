package com.pk.petrolstationmonolith.services.monitoring;

import com.pk.petrolstationmonolith.entities.monitoring.Monitoring;
import com.pk.petrolstationmonolith.properties.monitoring.MonitoringProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

@Component
@AllArgsConstructor
@Slf4j
public class ScheduledMonitoringTask implements SchedulingConfigurer {

    private final MonitoringService monitoringService;
    private final MonitoringProperties properties;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(
                this::scheduledTask,
                triggerContext -> {
                    Calendar nextExecutionTime = new GregorianCalendar();
                    java.util.Date lastActualExecutionTime = triggerContext.lastActualExecutionTime();
                    nextExecutionTime.setTime(lastActualExecutionTime != null ? lastActualExecutionTime : new java.util.Date());
                    nextExecutionTime.add(Calendar.MILLISECOND, properties.getInterval());
                    return nextExecutionTime.getTime();
                }
        );
        scheduledTask();
    }

    private void scheduledTask() {
        log.trace("Executing scheduled monitoring task with interval " + properties.getInterval());
        Monitoring monitoring = new Monitoring();
        monitoring.setDateTime(LocalDateTime.now());
        monitoring.setE95Temperature(getRandomTemperature());
        monitoring.setE95Pressure(getRandomPressure());
        monitoring.setE95Level(getRandomLevel());
        monitoring.setE98Temperature(getRandomTemperature());
        monitoring.setE98Pressure(getRandomPressure());
        monitoring.setE98Level(getRandomLevel());
        monitoring.setOnTemperature(getRandomTemperature());
        monitoring.setOnPressure(getRandomPressure());
        monitoring.setOnLevel(getRandomLevel());
        monitoring.setLpgTemperature(getRandomTemperature());
        monitoring.setLpgPressure(getRandomPressure());
        monitoring.setLpgLevel(getRandomLevel());
        monitoringService.addParameter(monitoring);
    }

    private int getRandomTemperature() {
        return new Random().nextInt(19) + 10;
    }

    private int getRandomPressure() {
        return new Random().nextInt(99) + 200;
    }

    private int getRandomLevel() {
        return new Random().nextInt(69) + 31;
    }


}
