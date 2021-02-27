package com.pk.petrolstationmonolith.entities.monitoring;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "parameter")
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

    public Parameter() {
    }

    public Parameter(LocalDateTime dateTime, int e95Pressure, int e95Temperature, int e95Level, int e98Pressure,
                     int e98Temperature, int e98Level, int onPressure, int onTemperature, int onLevel,
                     int lpgPressure, int lpgTemperature, int lpgLevel) {
        this.dateTime = dateTime;
        this.e95Pressure = e95Pressure;
        this.e95Temperature = e95Temperature;
        this.e95Level = e95Level;
        this.e98Pressure = e98Pressure;
        this.e98Temperature = e98Temperature;
        this.e98Level = e98Level;
        this.onPressure = onPressure;
        this.onTemperature = onTemperature;
        this.onLevel = onLevel;
        this.lpgPressure = lpgPressure;
        this.lpgTemperature = lpgTemperature;
        this.lpgLevel = lpgLevel;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime timestamp) {
        this.dateTime = timestamp;
    }

    public int getE95Pressure() {
        return e95Pressure;
    }

    public void setE95Pressure(int e95Pressure) {
        this.e95Pressure = e95Pressure;
    }

    public int getE95Temperature() {
        return e95Temperature;
    }

    public void setE95Temperature(int e95Temperature) {
        this.e95Temperature = e95Temperature;
    }

    public int getE95Level() {
        return e95Level;
    }

    public void setE95Level(int e95Level) {
        this.e95Level = e95Level;
    }

    public int getE98Pressure() {
        return e98Pressure;
    }

    public void setE98Pressure(int e98Pressure) {
        this.e98Pressure = e98Pressure;
    }

    public int getE98Temperature() {
        return e98Temperature;
    }

    public void setE98Temperature(int e98Temperature) {
        this.e98Temperature = e98Temperature;
    }

    public int getE98Level() {
        return e98Level;
    }

    public void setE98Level(int e98Level) {
        this.e98Level = e98Level;
    }

    public int getOnPressure() {
        return onPressure;
    }

    public void setOnPressure(int onPressure) {
        this.onPressure = onPressure;
    }

    public int getOnTemperature() {
        return onTemperature;
    }

    public void setOnTemperature(int onTemperature) {
        this.onTemperature = onTemperature;
    }

    public int getOnLevel() {
        return onLevel;
    }

    public void setOnLevel(int onLevel) {
        this.onLevel = onLevel;
    }

    public int getLpgPressure() {
        return lpgPressure;
    }

    public void setLpgPressure(int lpgPressure) {
        this.lpgPressure = lpgPressure;
    }

    public int getLpgTemperature() {
        return lpgTemperature;
    }

    public void setLpgTemperature(int lpgTemperature) {
        this.lpgTemperature = lpgTemperature;
    }

    public int getLpgLevel() {
        return lpgLevel;
    }

    public void setLpgLevel(int lpgLevel) {
        this.lpgLevel = lpgLevel;
    }

}
