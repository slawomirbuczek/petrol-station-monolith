package com.pk.petrolstationmonolith.entities.carwash;

import com.pk.petrolstationmonolith.entities.account.User;
import com.pk.petrolstationmonolith.enums.carwash.WashingType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime dateTime;

    @Enumerated(EnumType.STRING)
    private WashingType washingType;

    @OneToOne
    private User user;

    public Reservation() {
    }

    public Reservation(LocalDateTime dateTime, WashingType washingType, User user) {
        this.dateTime = dateTime;
        this.washingType = washingType;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public WashingType getWashingType() {
        return washingType;
    }

    public void setWashingType(WashingType washingType) {
        this.washingType = washingType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
