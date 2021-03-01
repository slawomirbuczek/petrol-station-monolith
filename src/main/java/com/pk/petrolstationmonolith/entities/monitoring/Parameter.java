package com.pk.petrolstationmonolith.entities.monitoring;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "parameter")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Parameter {

    @Id
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTime;

    private int e95Pressure;

    private int e95Temperature;

    private int e95Level;

    private int e98Pressure;

    private int e98Temperature;

    private int e98Level;

    private int onPressure;

    private int onTemperature;

    private int onLevel;

    private int lpgPressure;

    private int lpgTemperature;

    private int lpgLevel;

}
