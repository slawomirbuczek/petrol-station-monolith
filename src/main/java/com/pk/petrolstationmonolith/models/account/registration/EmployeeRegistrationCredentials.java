package com.pk.petrolstationmonolith.models.account.registration;

import com.pk.petrolstationmonolith.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRegistrationCredentials {

    @Positive(message = "Customer id must be a positive number")
    private long customerId;

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

}
