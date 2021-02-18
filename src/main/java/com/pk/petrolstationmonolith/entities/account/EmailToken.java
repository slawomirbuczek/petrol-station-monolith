package com.pk.petrolstationmonolith.entities.account;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "email_token")
public class EmailToken {

    @Id
    @GeneratedValue
    private Long id;

    private UUID token;

    @OneToOne
    private User user;

    public EmailToken() {
    }

    public EmailToken(Long id, UUID token, User user) {
        this.id = id;
        this.token = token;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public UUID getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
