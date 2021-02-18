package com.pk.petrolstationmonolith.models.monitoring;

import javax.validation.constraints.Min;

public class RequestChangeInterval {

    @Min(0)
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
