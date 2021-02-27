package com.pk.petrolstationmonolith.models.carwash;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class ReservationDetails {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTime;

    private long userId;

    private String name;

    public ReservationDetails() {
    }

    public ReservationDetails(LocalDateTime dateTime, long userId, String name) {
        this.dateTime = dateTime;
        this.userId = userId;
        this.name = name;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
