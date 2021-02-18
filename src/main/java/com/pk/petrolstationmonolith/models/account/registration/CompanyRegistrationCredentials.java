package com.pk.petrolstationmonolith.models.account.registration;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CompanyRegistrationCredentials {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Email(message = "Email should be valid")
    private String email;

    @Size(min = 9, max = 9, message = "Regon should be valid")
    private String regon;

    @Size(min = 10, max = 10, message = "Nip should be valid")
    private String nip;

    @NotBlank(message = "Country name cannot be blank")
    private String country;

    @NotBlank(message = "City name cannot be blank")
    private String city;

    @NotBlank(message = "Zip cannot be blank")
    private String zip;

    @NotBlank(message = "Street name cannot be blank")
    private String street;

    @NotBlank(message = "Building number cannot be blank")
    private String buildingNumber;

    private String apartmentNumber;

    public CompanyRegistrationCredentials() {
    }

    public CompanyRegistrationCredentials(String name, String email, String regon, String nip, String country, String city,
                                          String zip, String street, String buildingNumber, String apartmentNumber) {
        this.name = name;
        this.email = email;
        this.regon = regon;
        this.nip = nip;
        this.country = country;
        this.city = city;
        this.zip = zip;
        this.street = street;
        this.buildingNumber = buildingNumber;
        this.apartmentNumber = apartmentNumber;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getRegon() {
        return regon;
    }

    public String getNip() {
        return nip;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getZip() {
        return zip;
    }

    public String getStreet() {
        return street;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRegon(String regon) {
        this.regon = regon;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

}
