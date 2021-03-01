package com.pk.petrolstationmonolith.models.supplies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestGetSuppliesByDateBetween {

    @NotNull(message = "Date cannot be null")
    private LocalDate from;

    @NotNull(message = "Date cannot be null")
    private LocalDate to;

}
