package com.pk.petrolstationmonolith.dtos.carwash;

import com.pk.petrolstationmonolith.enums.carwash.WashingType;

import java.sql.Date;
import java.sql.Time;

public class ReservationDto {

    private Date date;

    private Time time;

    private WashingType washingType;

    private Long userId;

    public ReservationDto() {
    }

    public ReservationDto(Date date, Time time, WashingType washingType, Long userId) {
        this.date = date;
        this.time = time;
        this.washingType = washingType;
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    public WashingType getWashingType() {
        return washingType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void setWashingType(WashingType washingType) {
        this.washingType = washingType;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
