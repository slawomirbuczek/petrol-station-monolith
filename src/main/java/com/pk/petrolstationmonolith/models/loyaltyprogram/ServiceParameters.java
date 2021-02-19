package com.pk.petrolstationmonolith.models.loyaltyprogram;

public class ServiceParameters {

    private String service;
    private long points;
    private long cost;

    public ServiceParameters() {
    }

    public ServiceParameters(String service, long points, long cost) {
        this.service = service;
        this.points = points;
        this.cost = cost;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
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
