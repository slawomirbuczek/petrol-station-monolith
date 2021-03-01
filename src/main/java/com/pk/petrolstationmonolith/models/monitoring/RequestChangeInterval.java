package com.pk.petrolstationmonolith.models.monitoring;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestChangeInterval {

    @Positive(message = "Interval must be positive number")
    private int interval;

}
