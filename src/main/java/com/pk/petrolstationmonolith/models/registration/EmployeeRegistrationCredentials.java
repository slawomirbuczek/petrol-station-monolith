package com.pk.petrolstationmonolith.models.registration;

import javax.validation.constraints.*;
import java.sql.Date;

public class EmployeeRegistrationCredentials {

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

    @Pattern(regexp = "^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$",
            message = "Phone number should be valid")
    private String phoneNumber;

    @NotNull(message = "Date of birth cannot be null")
    private Date dateOfBirth;

    @Min(value = 0, message = "Salary should be valid")
    private int salary;

    @Pattern(regexp = "^[A-Z]{2}[0-9]{2}[a-zA-Z0-9]{10,30}$", message = "Bank account number should be valid")
    private String accountNumber;

    @NotNull(message = "Start date of work cannot be null")
    private String startDateOfWork;

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

    public EmployeeRegistrationCredentials() {
    }

    public EmployeeRegistrationCredentials(String firstName, String lastName, String email, String pesel, String nip,
                                           String phoneNumber, Date dateOfBirth, int salary, String accountNumber,
                                           String startDateOfWork, String country, String city, String zip, String street,
                                           String buildingNumber, String apartmentNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.pesel = pesel;
        this.nip = nip;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.salary = salary;
        this.accountNumber = accountNumber;
        this.startDateOfWork = startDateOfWork;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public int getSalary() {
        return salary;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getStartDateOfWork() {
        return startDateOfWork;
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

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setStartDateOfWork(String startDateOfWork) {
        this.startDateOfWork = startDateOfWork;
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
