package com.pk.petrolstationmonolith.models.account.registration;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class IndividualRegistrationCredentials {

    @NotBlank(message = "First name cannot be blank")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    private String lastName;

    @Email(message = "Email should be valid")
    private String email;

    @Size(min = 11, max = 11, message = "Pesel should be valid")
    private String pesel;

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

    public IndividualRegistrationCredentials() {
    }

    public IndividualRegistrationCredentials(String firstName, String lastName, String email, String pesel,
                                             String nip, String country, String city, String zip, String street,
                                             String buildingNumber, String apartmentNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.pesel = pesel;
        this.nip = nip;
        this.country = country;
        this.city = city;
        this.zip = zip;
        this.street = street;
        this.buildingNumber = buildingNumber;
        this.apartmentNumber = apartmentNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPesel() {
        return pesel;
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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
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
