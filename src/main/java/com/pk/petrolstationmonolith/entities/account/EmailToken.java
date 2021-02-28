package com.pk.petrolstationmonolith.entities.account;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "email_token")
public class EmailToken {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID token;

    private LocalDateTime creationDateTime;

    @OneToOne
    private User user;

    public EmailToken() {
    }

    public EmailToken(User user) {
        this.user = user;
        creationDateTime = LocalDateTime.now();
    }

    public EmailToken(UUID token, User user) {
        this.token = token;
        this.user = user;
        creationDateTime = LocalDateTime.now();
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
