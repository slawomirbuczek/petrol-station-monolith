package com.pk.petrolstationmonolith.models.carwash;

import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class RequestCancelReservation {

    @NotNull
    private LocalDateTime dateTime;

    public RequestCancelReservation() {
    }

    public RequestCancelReservation(@NotNull LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

}
