package com.pk.petrolstationmonolith.exceptions.account;

public class EmployeeNotFoundException extends RuntimeException{

    public EmployeeNotFoundException(long id) {
        super("Employee with id: " + id + " not found");
    }
}
