package com.pk.petrolstationmonolith.entities.transactions;

import com.pk.petrolstationmonolith.entities.account.User;
import com.pk.petrolstationmonolith.enums.pricelist.ServiceType;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ServiceType serviceType;

    private Date date;

    private Time time;

    private int number;

    private double cost;

    @OneToOne
    private User user;

    public Transaction() {
    }

    public Transaction(Long id, ServiceType serviceType, Date date, Time time, int number, double cost, User user) {
        this.id = id;
        this.serviceType = serviceType;
        this.date = date;
        this.time = time;
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

    public Date getDate() {
        return date;
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
