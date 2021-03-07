package com.pk.petrolstationmonolith.dtos.monitoring;

import com.pk.petrolstationmonolith.enums.FuelType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParametersDto {

    @NotNull(message = "Fuel type cannot be null")
    private FuelType fuelType;

    @NotNull(message = "Date cannot be null")
    private LocalDateTime dateTime;

    @Positive(message = "Tank number must be postivie number")
    private Integer tankNumber;

    @Positive(message = "Pressure must be postive number")
    private Float pressure;

    @NotNull(message = "Temperature cannot be null")
    private Float temperature;

    @Positive(message = "Level must be postivie number")
    private Float level;

}
