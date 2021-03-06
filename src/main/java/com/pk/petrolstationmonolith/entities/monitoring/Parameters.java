package com.pk.petrolstationmonolith.entities.monitoring;

import com.pk.petrolstationmonolith.enums.FuelType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Parameters")
public class Parameters {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "FuelType")
    private FuelType fuelType;

    @Column(name = "DateTime")
    private LocalDateTime dateTime;

    @Column(name = "Pressure")
    private Float pressure;

    @Column(name = "Temperature")
    private Float temperature;

    @Column(name = "Level")
    private Float level;

}
