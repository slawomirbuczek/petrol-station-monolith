package com.pk.petrolstationmonolith.dtos.transactions;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pk.petrolstationmonolith.enums.ServiceType;

import java.time.LocalDateTime;

public class TransactionDto {

    private Long id;

    private ServiceType serviceType;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTime;

    private int number;

    private double cost;

    public TransactionDto() {
    }

    public TransactionDto(Long id, ServiceType serviceType, LocalDateTime dateTime, int number, double cost) {
        this.id = id;
        this.serviceType = serviceType;
        this.dateTime = dateTime;
        this.number = number;
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
