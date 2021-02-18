package com.pk.petrolstationmonolith.models.carwash;

import com.pk.petrolstationmonolith.enums.carwash.WashingType;

import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Time;

public class RequestReserve {

    @NotNull
    private Date date;

    @NotNull
    private Time time;

    @NotNull
    private WashingType washingType;

    public RequestReserve() {
    }

    public RequestReserve(Date date, Time time, WashingType washingType) {
        this.date = date;
        this.time = time;
        this.washingType = washingType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public WashingType getWashingType() {
        return washingType;
    }

    public void setWashingType(WashingType washingType) {
        this.washingType = washingType;
    }
}
