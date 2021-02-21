package com.pk.petrolstationmonolith.models.monitoring;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class RequestParametersBetweenDates {

    @NotNull
    private LocalDate from;

    @NotNull
    private LocalDate to;

    public RequestParametersBetweenDates() {
    }

    public RequestParametersBetweenDates(LocalDate from, LocalDate to) {
        this.from = from;
        this.to = to;
    }

    public LocalDate getFrom() {
        return from;
    }

    public void setFrom(LocalDate from) {
        this.from = from;
    }

    public LocalDate getTo() {
        return to;
    }

    public void setTo(LocalDate to) {
        this.to = to;
    }

}
