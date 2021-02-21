package com.pk.petrolstationmonolith.entities.transactions;

import com.pk.petrolstationmonolith.entities.account.User;
import com.pk.petrolstationmonolith.enums.pricelist.ServiceType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ServiceType serviceType;

    private LocalDateTime dateTime;

    private int number;

    private double cost;

    @OneToOne
    private User user;

    public Transaction() {
    }

    public Transaction(ServiceType serviceType, LocalDateTime dateTime, int number, double cost, User user) {
        this.serviceType = serviceType;
        this.dateTime = dateTime;
        this.number = number;
        this.cost = cost;
        this.user = user;
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

    public void setDateTime(LocalDateTime timestamp) {
        this.dateTime = timestamp;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
