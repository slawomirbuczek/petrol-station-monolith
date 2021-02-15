package com.pk.petrolstationmonolith.entities.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.sql.Date;

@Entity
public class Employee {

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;

    private String lastName;

    private String pesel;

    private String nip;

    private String phoneNumber;

    private Date dateOfBirth;

    private int salary;

    private String accountNumber;

    private String startDateOfWork;

    @OneToOne
    private User user;

    @OneToOne
    private Address address;

    public Employee() {
    }

    public Employee(String firstName, String lastName, String pesel, String nip, String phoneNumber, Date dateOfBirth,
                    int salary, String accountNumber, String startDateOfWork, User user, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.nip = nip;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.salary = salary;
        this.accountNumber = accountNumber;
        this.startDateOfWork = startDateOfWork;
        this.user = user;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
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

    public User getUser() {
        return user;
    }

    public Address getAddress() {
        return address;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public void setUser(User user) {
        this.user = user;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}
