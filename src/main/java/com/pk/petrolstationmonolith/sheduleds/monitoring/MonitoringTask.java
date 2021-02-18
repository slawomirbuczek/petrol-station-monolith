package com.pk.petrolstationmonolith.sheduleds.monitoring;

import com.pk.petrolstationmonolith.entities.monitoring.*;
import com.pk.petrolstationmonolith.properties.monitoring.MonitoringProperties;
import com.pk.petrolstationmonolith.repositories.monitoring.E95Repository;
import com.pk.petrolstationmonolith.repositories.monitoring.E98Repository;
import com.pk.petrolstationmonolith.repositories.monitoring.LpgRepository;
import com.pk.petrolstationmonolith.repositories.monitoring.OnRepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

@Configuration
@EnableScheduling
public class MonitoringTask implements SchedulingConfigurer {

    private final E95Repository e95Repository;
    private final E98Repository e98Repository;
    private final OnRepository onRepository;
    private final LpgRepository lpgRepository;
    private final MonitoringProperties properties;

    public MonitoringTask(E95Repository e95Repository, E98Repository e98Repository,
                          OnRepository onRepository, LpgRepository lpgRepository,
                          MonitoringProperties properties) {
        this.e95Repository = e95Repository;
        this.e98Repository = e98Repository;
        this.onRepository = onRepository;
        this.lpgRepository = lpgRepository;
        this.properties = properties;
    }

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
        ModelMapper mapper = new ModelMapper();

        E95 e95 = mapper.map(createNewRandomParameter(), E95.class);
        e95Repository.save(e95);

        E98 e98 = mapper.map(createNewRandomParameter(), E98.class);
        e98Repository.save(e98);

        On on = mapper.map(createNewRandomParameter(), On.class);
        onRepository.save(on);

        Lpg lpg = mapper.map(createNewRandomParameter(), Lpg.class);
        lpgRepository.save(lpg);
    }

    private Parameter createNewRandomParameter() {
        Parameter parameter = new Parameter();
        parameter.setDate(new Date(System.currentTimeMillis()));
        parameter.setTime(Time.valueOf(LocalTime.now()));
        parameter.setTemperature(getRandomTemperature());
        parameter.setPressure(getRandomPressure());
        parameter.setLevel(getRandomLevel());
        return parameter;
    }

    private int getRandomTemperature() {
        return new Random().nextInt(20) + 10;
    }

    private int getRandomPressure() {
        return new Random().nextInt(100) + 100;
    }

    private int getRandomLevel() {
        return new Random().nextInt(100);
    }


}
