package com.pk.petrolstationmonolith.models.transactions;

import com.pk.petrolstationmonolith.enums.ServiceType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

public class RequestAddTransaction {

    @NotNull
    private ServiceType serviceType;

    @NotNull
    private LocalDateTime dateTime;

    @Positive
    private int number;

    @Positive
    private double cost;

    public RequestAddTransaction() {
    }

    public RequestAddTransaction(ServiceType serviceType, LocalDateTime dateTime, int number, double cost) {
        this.serviceType = serviceType;
        this.dateTime = dateTime;
        this.number = number;
        this.cost = cost;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
