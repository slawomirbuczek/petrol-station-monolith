package com.pk.petrolstationmonolith.entities.monitoring;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.sql.Date;
import java.sql.Time;

@MappedSuperclass
public class Parameter {

    @Id
    @GeneratedValue
    private Long id;

    private Date date;

    private Time time;

    private int pressure;

    private int temperature;

    private int level;

    public Parameter() {
    }

    public Parameter(Long id, Date date, Time time, int pressure, int temperature, int level) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.pressure = pressure;
        this.temperature = temperature;
        this.level = level;
    }

    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    public int getPressure() {
        return pressure;
    }

    public int getTemperature() {
        return temperature;
    }

    public int getLevel() {
        return level;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
