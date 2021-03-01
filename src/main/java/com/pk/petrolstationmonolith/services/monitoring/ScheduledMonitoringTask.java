package com.pk.petrolstationmonolith.services.monitoring;

import com.pk.petrolstationmonolith.entities.monitoring.Parameter;
import com.pk.petrolstationmonolith.properties.monitoring.MonitoringProperties;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

@Component
@AllArgsConstructor
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
        Parameter parameter = new Parameter();
        parameter.setDateTime(LocalDateTime.now());
        parameter.setE95Temperature(getRandomTemperature());
        parameter.setE95Pressure(getRandomPressure());
        parameter.setE95Level(getRandomLevel());
        parameter.setE98Temperature(getRandomTemperature());
        parameter.setE98Pressure(getRandomPressure());
        parameter.setE98Level(getRandomLevel());
        parameter.setOnTemperature(getRandomTemperature());
        parameter.setOnPressure(getRandomPressure());
        parameter.setOnLevel(getRandomLevel());
        parameter.setLpgTemperature(getRandomTemperature());
        parameter.setLpgPressure(getRandomPressure());
        parameter.setLpgLevel(getRandomLevel());
        monitoringService.addParameter(parameter);
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
