package com.pk.petrolstationmonolith.entities.account;

import javax.persistence.*;

@Entity
public class Individual {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String pesel;

    private String nip;

    @OneToOne
    private User user;

    public Individual() {
    }

    public Individual(String firstName, String lastName, String pesel, String nip, User user) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.nip = nip;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
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
