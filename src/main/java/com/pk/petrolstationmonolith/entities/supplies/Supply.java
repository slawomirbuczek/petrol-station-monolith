package com.pk.petrolstationmonolith.entities.supplies;

import com.pk.petrolstationmonolith.enums.supplies.FuelType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "supply")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Supply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private FuelType fuelType;

    private LocalDate date;

    private int amount;

    private float price;

}
