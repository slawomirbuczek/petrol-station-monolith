package com.pk.petrolstationmonolith.dtos.account;

import java.sql.Date;

public class EmployeeDto {

    private String phoneNumber;

    private Date dateOfBirth;

    private int salary;

    private String accountNumber;

    private String startDateOfWork;

    public EmployeeDto() {
    }

    public EmployeeDto(String phoneNumber, Date dateOfBirth, int salary, String accountNumber, String startDateOfWork) {
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.salary = salary;
        this.accountNumber = accountNumber;
        this.startDateOfWork = startDateOfWork;
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

}
