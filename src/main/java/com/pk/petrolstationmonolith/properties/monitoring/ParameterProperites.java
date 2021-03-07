package com.pk.petrolstationmonolith.properties.monitoring;

import lombok.Data;

@Data
public class ParameterProperites {

    /**
     * Minimum level (in percentage) of fuel in tank.
     */
    private float minLevel = 30;

    /**
     * Maximum allowable temperature (in celsius) in fuel tank.
     */
    private float maxTemperature = 20;

    /**
     * Maximum allowable pressure (in bars) in fuel tank.
     */
    private float highPressure = 300;



}
