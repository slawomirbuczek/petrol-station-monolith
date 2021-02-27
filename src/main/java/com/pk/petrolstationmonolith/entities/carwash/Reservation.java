package com.pk.petrolstationmonolith.entities.carwash;

import com.pk.petrolstationmonolith.entities.account.User;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    private LocalDateTime dateTime;

    @OneToOne
    private User user;

    public Reservation() {
    }

    public Reservation(LocalDateTime dateTime, User user) {
        this.dateTime = dateTime;
        this.user = user;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
