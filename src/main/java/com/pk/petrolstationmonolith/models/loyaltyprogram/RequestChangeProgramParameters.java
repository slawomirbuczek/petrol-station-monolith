package com.pk.petrolstationmonolith.models.loyaltyprogram;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestChangeProgramParameters {

    @Positive
    private long points;

    @Positive
    private long cost;

}
