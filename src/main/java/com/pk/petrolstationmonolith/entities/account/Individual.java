package com.pk.petrolstationmonolith.entities.account;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Individual {

    @Id
    @GeneratedValue
    private Long id;

    private String pesel;

    private String nip;

    @OneToOne
    private User user;

    @OneToOne
    private Address address;

    public Individual() {
    }

    public Individual(Long id, String pesel, String nip, User user, Address address) {
        this.id = id;
        this.pesel = pesel;
        this.nip = nip;
        this.user = user;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public String getPesel() {
        return pesel;
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

    public void setPesel(String pesel) {
        this.pesel = pesel;
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
