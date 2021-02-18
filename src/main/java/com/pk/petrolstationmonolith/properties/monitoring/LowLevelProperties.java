package com.pk.petrolstationmonolith.properties.monitoring;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("monitoring.low-level")
public class LowLevelProperties {

    /**
     * Minimum level (in percentage) of E95 fuel in tank.
     */
    private int e95;

    /**
     * Minimum level (in percentage) of E98 fuel in tank.
     */
    private int e98;

    /**
     * Minimum level (in percentage) of ON fuel in tank.
     */
    private int on;

    /**
     * Minimum level (in percentage) of LPG in tank.
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
