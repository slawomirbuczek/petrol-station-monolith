package com.pk.petrolstationmonolith.entities.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "individual")
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    public Individual(String firstName, String lastName, String pesel, String nip, User user) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.nip = nip;
        this.user = user;
    }

}
