package com.pk.petrolstationmonolith.entities.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    public Employee(String phoneNumber, Date dateOfBirth, int salary, String accountNumber,
                    String startDateOfWork, User user) {
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.salary = salary;
        this.accountNumber = accountNumber;
        this.startDateOfWork = startDateOfWork;
        this.user = user;
    }

}
