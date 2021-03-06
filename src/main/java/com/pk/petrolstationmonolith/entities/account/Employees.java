package com.pk.petrolstationmonolith.entities.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Employees")
public class Employees {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "PhoneNumber")
    private String phoneNumber;

    @Column(name = "DateOfBirth")
    private LocalDate dateOfBirth;

    @Column(name = "Salary")
    private Integer salary;

    @Column(name = "AccountNumber")
    private String accountNumber;

    @Column(name = "StartDateOfWork")
    private LocalDate startDateOfWork;

    @OneToOne
    private Customers customers;

    public Employees(String phoneNumber, LocalDate dateOfBirth, Integer salary, String accountNumber,
                     LocalDate startDateOfWork, Customers customers) {
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.salary = salary;
        this.accountNumber = accountNumber;
        this.startDateOfWork = startDateOfWork;
        this.customers = customers;
    }
}
