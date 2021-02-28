package com.pk.petrolstationmonolith.models.account.registration;

import com.pk.petrolstationmonolith.enums.account.Roles;

import javax.validation.constraints.*;
import java.sql.Date;

public class EmployeeRegistrationCredentials {

    @Positive(message = "User id must be a positive number")
    private long userId;

    @NotNull(message = "Role cannob be null")
    private Roles role;

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

    public EmployeeRegistrationCredentials() {
    }

    public EmployeeRegistrationCredentials(long userId, Roles role, String phoneNumber, Date dateOfBirth, int salary, String accountNumber,
                                           String startDateOfWork) {
        this.userId = userId;
        this.role = role;
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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }
}
