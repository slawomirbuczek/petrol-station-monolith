package com.pk.petrolstationmonolith.models.transactions;

import com.pk.petrolstationmonolith.enums.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestAddTransaction {

    @NotNull(message = "Service type cannot be null")
    private ServiceType serviceType;

    @NotNull(message = "Date cannot be null")
    private LocalDateTime dateTime;

    @Positive(message = "Number must be positive")
    private int number;

    @Positive(message = "Cost must be positive number")
    private double cost;

}
