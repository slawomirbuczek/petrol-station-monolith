package com.pk.petrolstationmonolith.exceptions.account.employee;

public class EmployeeNotFoundException extends RuntimeException{

    public EmployeeNotFoundException(long id) {
        super("Employee with id: " + id + " not found");
    }
}
