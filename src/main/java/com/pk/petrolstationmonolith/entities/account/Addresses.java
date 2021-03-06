package com.pk.petrolstationmonolith.entities.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Addresses")
public class Addresses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Country")
    private String country;

    @Column(name = "City")
    private String city;

    @Column(name = "Zip")
    private String zip;

    @Column(name = "Street")
    private String street;

    @Column(name = "BuildingNumber")
    private String buildingNumber;

    @Column(name = "ApartmentNumber")
    private String apartmentNumber;

    @OneToOne
    private Customers customers;

    public Addresses(String country, String city, String zip, String street, String buildingNumber, String apartmentNumber, Customers customers) {
        this.country = country;
        this.city = city;
        this.zip = zip;
        this.street = street;
        this.buildingNumber = buildingNumber;
        this.apartmentNumber = apartmentNumber;
        this.customers = customers;
    }

}
