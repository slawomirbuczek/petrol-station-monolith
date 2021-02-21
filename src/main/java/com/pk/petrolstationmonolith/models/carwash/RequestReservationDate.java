package com.pk.petrolstationmonolith.models.carwash;

import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.time.LocalDate;

public class RequestReservationDate {

    @NotNull
    private LocalDate date;

    public RequestReservationDate() {
    }

    public RequestReservationDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}
