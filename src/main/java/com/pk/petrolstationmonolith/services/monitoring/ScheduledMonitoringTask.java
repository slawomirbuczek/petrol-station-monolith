package com.pk.petrolstationmonolith.services.monitoring;

import com.pk.petrolstationmonolith.dtos.monitoring.ParametersDto;
import com.pk.petrolstationmonolith.enums.FuelType;
import com.pk.petrolstationmonolith.properties.monitoring.MonitoringProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
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
        Arrays.stream(FuelType.values()).forEach(
                fuelType -> monitoringService.addParameter(getRandomParameterDto(fuelType))
        );
    }

    private ParametersDto getRandomParameterDto(FuelType fuelType) {
        return new ParametersDto(
                fuelType, LocalDateTime.now(), 1, getRandomPressure(), getRandomTemperature(), getRandomLevel()
        );
    }

    private float getRandomTemperature() {
        return getRandomFloat(10, 19);
    }

    private float getRandomPressure() {
        return getRandomFloat(200, 299);
    }

    private float getRandomLevel() {
        return getRandomFloat(31, 100);
    }

    private float getRandomFloat(int min, int max) {
        return new Random().nextFloat() * (max - min) + min;
    }

}
