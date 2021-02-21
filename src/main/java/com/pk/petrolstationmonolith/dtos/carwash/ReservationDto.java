package com.pk.petrolstationmonolith.dtos.carwash;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pk.petrolstationmonolith.enums.carwash.WashingType;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ReservationDto {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTime;

    private WashingType washingType;

    private Long userId;

    public ReservationDto() {
    }

    public ReservationDto(LocalDateTime dateTime, WashingType washingType, Long userId) {
        this.dateTime = dateTime;
        this.washingType = washingType;
        this.userId = userId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public WashingType getWashingType() {
        return washingType;
    }

    public void setWashingType(WashingType washingType) {
        this.washingType = washingType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
