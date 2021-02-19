package com.pk.petrolstationmonolith.models.monitoring;

import javax.validation.constraints.Positive;

public class RequestChangeInterval {

    @Positive
    private int interval;

    public RequestChangeInterval() {
    }

    public RequestChangeInterval(int interval) {
        this.interval = interval;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

}
