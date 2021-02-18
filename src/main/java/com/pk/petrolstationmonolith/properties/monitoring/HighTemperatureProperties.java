package com.pk.petrolstationmonolith.properties.monitoring;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("monitoring.high-temperature")
public class HighTemperatureProperties {

    /**
     * Maximum allowable temperature (in celsius) in the E95 fuel tank.
     */
    private int e95;

    /**
     * Maximum allowable temperature (in celsius) in the E98 fuel tank.
     */
    private int e98;

    /**
     * Maximum allowable temperature (in celsius) in the ON fuel tank.
     */
    private int on;

    /**
     * Maximum allowable temperature (in celsius) in the LPG tank.
     */
    private int lpg;

    public int getE95() {
        return e95;
    }

    public int getE98() {
        return e98;
    }

    public int getOn() {
        return on;
    }

    public int getLpg() {
        return lpg;
    }

    public void setE95(int e95) {
        this.e95 = e95;
    }

    public void setE98(int e98) {
        this.e98 = e98;
    }

    public void setOn(int on) {
        this.on = on;
    }

    public void setLpg(int lpg) {
        this.lpg = lpg;
    }

}
