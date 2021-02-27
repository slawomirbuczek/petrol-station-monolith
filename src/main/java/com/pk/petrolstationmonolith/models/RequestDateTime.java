package com.pk.petrolstationmonolith.models;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class RequestDateTime {

    @NotNull
    private LocalDateTime dateTime;

    public RequestDateTime() {
    }

    public RequestDateTime(@NotNull LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

}
