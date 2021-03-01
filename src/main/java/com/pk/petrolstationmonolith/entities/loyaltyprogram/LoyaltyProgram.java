package com.pk.petrolstationmonolith.entities.loyaltyprogram;

import com.pk.petrolstationmonolith.entities.account.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "loyalty_program")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoyaltyProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long points;

    @OneToOne
    private User user;

}
