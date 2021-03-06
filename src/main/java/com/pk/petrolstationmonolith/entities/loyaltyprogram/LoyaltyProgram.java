package com.pk.petrolstationmonolith.entities.loyaltyprogram;

import com.pk.petrolstationmonolith.entities.account.Customers;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "LoyaltyProgram")
public class LoyaltyProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Points")
    private Long points;

    @OneToOne
    private Customers customers;

}
