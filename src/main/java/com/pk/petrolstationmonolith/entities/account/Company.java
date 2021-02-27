package com.pk.petrolstationmonolith.entities.account;

import javax.persistence.*;

@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String regon;

    private String nip;

    @OneToOne
    private User user;

    public Company() {
    }

    public Company(Long id, String name, String regon, String nip, User user) {
        this.id = id;
        this.name = name;
        this.regon = regon;
        this.nip = nip;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegon() {
        return regon;
    }

    public void setRegon(String regon) {
        this.regon = regon;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
