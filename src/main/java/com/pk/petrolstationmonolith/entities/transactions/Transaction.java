package com.pk.petrolstationmonolith.entities.transactions;

import com.pk.petrolstationmonolith.entities.account.User;
import com.pk.petrolstationmonolith.enums.ServiceType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
@Data
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ServiceType serviceType;

    private LocalDateTime dateTime;

    private int number;

    private double cost;

    @OneToOne
    private User user;

    public Transaction(ServiceType serviceType, LocalDateTime dateTime, int number, double cost, User user) {
        this.serviceType = serviceType;
        this.dateTime = dateTime;
        this.number = number;
        this.cost = cost;
        this.user = user;
    }

}
