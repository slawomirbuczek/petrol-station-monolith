package com.pk.petrolstationmonolith.dtos.account;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class EmployeeDto {

    private String phoneNumber;

    private Date dateOfBirth;

    private int salary;

    private String accountNumber;

    private String startDateOfWork;

}
