package com.pk.petrolstationmonolith.entities.transactions;

import com.pk.petrolstationmonolith.entities.account.Customers;
import com.pk.petrolstationmonolith.enums.ServiceType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Transactions")
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "ServiceType")
    private ServiceType serviceType;

    @Column(name = "DateTime")
    private LocalDateTime dateTime;

    @Column(name = "amount")
    private Integer number;

    @Column(name = "Cost")
    private Double cost;

    @ManyToOne
    private Customers customers;

    public Transactions(ServiceType serviceType, LocalDateTime dateTime, Integer number, Double cost, Customers customers) {
        this.serviceType = serviceType;
        this.dateTime = dateTime;
        this.number = number;
        this.cost = cost;
        this.customers = customers;
    }

}
