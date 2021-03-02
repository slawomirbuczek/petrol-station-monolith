package com.pk.petrolstationmonolith.dtos.supplies;

import com.pk.petrolstationmonolith.enums.FuelType;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class SupplyDto {

    @NotNull
    private FuelType fuelType;

    @NotNull
    private LocalDate date;

    @Positive
    private int amount;

    @Positive
    private float price;

}
