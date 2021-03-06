package com.pk.petrolstationmonolith.entities.monitoring;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Monitoring")
public class Monitoring {

    @Id
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "DateTime")
    private LocalDateTime dateTime;

    @Column(name = "E95Pressure")
    private int e95Pressure;

    @Column(name = "E95Temperature")
    private int e95Temperature;

    @Column(name = "E95Level")
    private int e95Level;

    @Column(name = "E98Pressure")
    private int e98Pressure;

    @Column(name = "E98Temperature")
    private int e98Temperature;

    @Column(name = "E98Level")
    private int e98Level;

    @Column(name = "OnPressure")
    private int onPressure;

    @Column(name = "OnTemperature")
    private int onTemperature;

    @Column(name = "OnLevel")
    private int onLevel;

    @Column(name = "LpgPressure")
    private int lpgPressure;

    @Column(name = "LpgTemperature")
    private int lpgTemperature;

    @Column(name = "LpgLevel")
    private int lpgLevel;

}
