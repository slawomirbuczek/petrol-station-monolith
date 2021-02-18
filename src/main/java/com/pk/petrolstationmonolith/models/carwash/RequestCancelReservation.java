package com.pk.petrolstationmonolith.models.carwash;

import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Time;

public class RequestCancelReservation {

    @NotNull
    private Date date;

    @NotNull
    private Time time;

    public RequestCancelReservation() {
    }

    public RequestCancelReservation(Date date, Time time) {
        this.date = date;
        this.time = time;
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
}
