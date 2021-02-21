package com.pk.petrolstationmonolith.dtos.transactions;

import com.pk.petrolstationmonolith.enums.pricelist.ServiceType;

import java.sql.Date;
import java.sql.Time;

public class TransactionDto {

    private ServiceType serviceType;

    private Date date;

    private Time time;

    private int number;

    private double cost;

    public TransactionDto() {
    }

    public TransactionDto(ServiceType serviceType, Date date, Time time, int number, double cost) {
        this.serviceType = serviceType;
        this.date = date;
        this.time = time;
        this.number = number;
        this.cost = cost;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
