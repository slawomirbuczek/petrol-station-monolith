package com.pk.petrolstationmonolith.entities.account;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String phoneNumber;

    private Date dateOfBirth;

    private int salary;

    private String accountNumber;

    private String startDateOfWork;

    @OneToOne
    private User user;

    public Employee() {
    }

    public Employee(String phoneNumber, Date dateOfBirth, int salary, String accountNumber,
                    String startDateOfWork, User user) {
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.salary = salary;
        this.accountNumber = accountNumber;
        this.startDateOfWork = startDateOfWork;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getStartDateOfWork() {
        return startDateOfWork;
    }

    public void setStartDateOfWork(String startDateOfWork) {
        this.startDateOfWork = startDateOfWork;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
