package com.pk.petrolstationmonolith.models.loyaltyprogram;

import javax.validation.constraints.Positive;

public class RequestChangeProgramParameters {

    @Positive
    private long points;

    @Positive
    private long cost;

    public RequestChangeProgramParameters() {
    }

    public RequestChangeProgramParameters(long points, long cost) {
        this.points = points;
        this.cost = cost;
    }

    public long getPoints() {
        return points;
    }

    public void setPoints(long points) {
        this.points = points;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }
}
