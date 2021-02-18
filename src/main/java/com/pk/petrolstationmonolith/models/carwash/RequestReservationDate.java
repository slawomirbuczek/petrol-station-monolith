package com.pk.petrolstationmonolith.models.carwash;

import javax.validation.constraints.NotNull;
import java.sql.Date;

public class RequestReservationDate {

    @NotNull
    private Date date;

    public RequestReservationDate() {
    }

    public RequestReservationDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
