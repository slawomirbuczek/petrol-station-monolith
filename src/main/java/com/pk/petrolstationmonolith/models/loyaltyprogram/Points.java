package com.pk.petrolstationmonolith.models.loyaltyprogram;

import javax.validation.constraints.Positive;

public class Points {

    @Positive
    private long points;

    public Points() {
    }

    public Points(long points) {
        this.points = points;
    }

    public long getPoints() {
        return points;
    }

    public void setPoints(long points) {
        this.points = points;
    }

}
