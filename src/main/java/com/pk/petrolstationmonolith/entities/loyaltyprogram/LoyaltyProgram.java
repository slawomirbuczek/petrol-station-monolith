package com.pk.petrolstationmonolith.entities.loyaltyprogram;

import com.pk.petrolstationmonolith.entities.account.User;

import javax.persistence.*;

@Entity
@Table(name = "loyalty_program")
public class LoyaltyProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long points;

    @OneToOne
    private User user;

    public LoyaltyProgram() {
    }

    public LoyaltyProgram(Long id, long points, User user) {
        this.id = id;
        this.points = points;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getPoints() {
        return points;
    }

    public void setPoints(long points) {
        this.points = points;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
