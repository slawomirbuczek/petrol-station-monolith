package com.pk.petrolstationmonolith.models.monitoring;

import com.pk.petrolstationmonolith.enums.FuelType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestParametersBetweenDates {

    @NotNull(message = "Fuel type cannot be null")
    private FuelType fuelType;

    @NotNull(message = "Date cannot be null")
    private LocalDate from;

    @NotNull(message = "Date cannot be null")
    private LocalDate to;

}
