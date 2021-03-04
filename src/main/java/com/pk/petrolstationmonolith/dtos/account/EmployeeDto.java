package com.pk.petrolstationmonolith.dtos.account;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.sql.Date;

@Data
@AllArgsConstructor
public class EmployeeDto {

    @NotBlank(message = "Phone number cannot be blank")
    private String phoneNumber;

    @NotNull(message = "Date of birth cannot be null")
    private Date dateOfBirth;

    @Positive(message = "Salary must be greater than 0")
    private int salary;

    @NotBlank(message = "Bank account number cannot be blank")
    private String accountNumber;

    @NotBlank(message = "Start date of work cannot be blank")
    private String startDateOfWork;

}
