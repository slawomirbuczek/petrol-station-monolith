package com.pk.petrolstationmonolith.models.loyaltyprogram;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceParameters {

    private String service;

    private long points;

    private long cost;

}
