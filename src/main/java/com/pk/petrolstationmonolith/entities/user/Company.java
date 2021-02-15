package com.pk.petrolstationmonolith.entities.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Company {

    @Id
    @GeneratedValue
    private Long id;

    private String regon;

    private String nip;

    @OneToOne
    private User user;

    @OneToOne
    private Address address;

    public Company() {
    }

    public Company(Long id, String regon, String nip, User user, Address address) {
        this.id = id;
        this.regon = regon;
        this.nip = nip;
        this.user = user;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public String getRegon() {
        return regon;
    }

    public String getNip() {
        return nip;
    }

    public User getUser() {
        return user;
    }

    public Address getAddress() {
        return address;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRegon(String regon) {
        this.regon = regon;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
