package com.pk.petrolstationmonolith.entities.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String country;

    private String city;

    private String zip;

    private String street;

    private String buildingNumber;

    private String apartmentNumber;

    @OneToOne
    private User user;

    public Address(String country, String city, String zip, String street, String buildingNumber, String apartmentNumber, User user) {
        this.country = country;
        this.city = city;
        this.zip = zip;
        this.street = street;
        this.buildingNumber = buildingNumber;
        this.apartmentNumber = apartmentNumber;
        this.user = user;
    }

}
