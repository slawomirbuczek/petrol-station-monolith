package com.pk.petrolstationmonolith.dtos.account;

public class AddressDto {

    private String country;

    private String city;

    private String zip;

    private String street;

    private String buildingNumber;

    private String apartmentNumber;

    public AddressDto() {
    }

    public AddressDto(String country, String city, String zip, String street, String buildingNumber, String apartmentNumber) {
        this.country = country;
        this.city = city;
        this.zip = zip;
        this.street = street;
        this.buildingNumber = buildingNumber;
        this.apartmentNumber = apartmentNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }
}
