package com.pk.petrolstationmonolith.models.carwash;

import com.pk.petrolstationmonolith.enums.carwash.WashingType;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class RequestReserve {

    private LocalDateTime dateTime;

    @NotNull
    private WashingType washingType;

    public RequestReserve() {
    }

    public RequestReserve(@NotNull LocalDateTime dateTime, @NotNull WashingType washingType) {
        this.dateTime = dateTime;
        this.washingType = washingType;
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
}
