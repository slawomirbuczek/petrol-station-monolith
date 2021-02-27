package com.pk.petrolstationmonolith.models.carwash;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class ResponseReservation {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTime;

    private boolean available;

    public ResponseReservation() {
    }

    public ResponseReservation(LocalDateTime dateTime, boolean available) {
        this.dateTime = dateTime;
        this.available = available;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

}
