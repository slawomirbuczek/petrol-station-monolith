package com.pk.petrolstationmonolith.enums;

import com.pk.petrolstationmonolith.models.loyaltyprogram.ServiceParameters;
import lombok.ToString;

@ToString
public enum ServiceType {

    E95(2, 100),
    E98(2, 100),
    ON(2, 100),
    LPG(1, 50),
    WASHING_STANDARD(5, 300),
    WASHING_WAXING(10, 400);

    private long points;
    private long cost;

    ServiceType(long points, long cost) {
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

    public ServiceParameters getServiceParameters() {
        return new ServiceParameters(name(), points, cost);
    }
}
