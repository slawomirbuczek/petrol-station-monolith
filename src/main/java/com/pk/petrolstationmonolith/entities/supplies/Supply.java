package com.pk.petrolstationmonolith.entities.supplies;

import com.pk.petrolstationmonolith.enums.FuelType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Supply")
public class Supply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "FuelType")
    private FuelType fuelType;

    @Column(name = "Date")
    private LocalDate date;

    @Column(name = "Amount")
    private Integer amount;

    @Column(name = "Price")
    private Float price;

}
