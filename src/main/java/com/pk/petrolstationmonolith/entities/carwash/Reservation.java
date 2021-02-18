package com.pk.petrolstationmonolith.entities.carwash;

import com.pk.petrolstationmonolith.entities.account.User;
import com.pk.petrolstationmonolith.enums.carwash.WashingType;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "timetable")
public class Reservation {

    @Id
    @GeneratedValue
    private Long id;

    private Date date;

    private Time time;

    @Enumerated(EnumType.STRING)
    private WashingType washingType;

    @OneToOne
    private User user;

    public Reservation() {
    }

    public Reservation(Long id, Date date, Time time, WashingType washingType, User user) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.washingType = washingType;
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
