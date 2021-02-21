package com.pk.petrolstationmonolith.properties.monitoring;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("monitoring")
public class MonitoringProperties {

    /**
     * Interval between monitoring in minutes.
     */
    private int interval;

    /**
     * Minimum level (in percentage) of E95 fuel in tank.
     */
    private int lowLevelE95 = 30;

    /**
     * Minimum level (in percentage) of E98 fuel in tank.
     */
    private int lowLevelE98 = 30;

    /**
     * Minimum level (in percentage) of ON fuel in tank.
     */
    private int lowLevelOn = 30;

    /**
     * Minimum level (in percentage) of LPG in tank.
     */
    private int lowLevelLpg = 30;

    /**
     * Maximum allowable temperature (in celsius) in the E95 fuel tank.
     */
    private int highTemperatureE95 = 30;

    /**
     * Maximum allowable temperature (in celsius) in the E98 fuel tank.
     */
    private int highTemperatureE98 = 30;

    /**
     * Maximum allowable temperature (in celsius) in the ON fuel tank.
     */
    private int highTemperatureOn = 30;

    /**
     * Maximum allowable temperature (in celsius) in the LPG tank.
     */
    private int highTemperatureLpg = 30;

    /**
     * Maximum allowable pressure (in bars) in the E95 fuel tank.
     */
    private int hightPressureE95 = 300;

    /**
     * Maximum allowable pressure (in bars) in the E98 fuel tank.
     */
    private int hightPressureE98 = 300;

    /**
     * Maximum allowable pressure (in bars) in the ON fuel tank.
     */
    private int hightPressureOn = 300;

    /**
     * Maximum allowable pressure (in bars) in the LPG tank.
     */
    private int hightPressureLpg = 300;

    public int getInterval() {
        return interval * 60 * 1000;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public int getLowLevelE95() {
        return lowLevelE95;
    }

    public void setLowLevelE95(int lowLevelE95) {
        this.lowLevelE95 = lowLevelE95;
    }

    public int getLowLevelE98() {
        return lowLevelE98;
    }

    public void setLowLevelE98(int lowLevelE98) {
        this.lowLevelE98 = lowLevelE98;
    }

    public int getLowLevelOn() {
        return lowLevelOn;
    }

    public void setLowLevelOn(int lowLevelOn) {
        this.lowLevelOn = lowLevelOn;
    }

    public int getLowLevelLpg() {
        return lowLevelLpg;
    }

    public void setLowLevelLpg(int lowLevelLpg) {
        this.lowLevelLpg = lowLevelLpg;
    }

    public int getHighTemperatureE95() {
        return highTemperatureE95;
    }

    public void setHighTemperatureE95(int highTemperatureE95) {
        this.highTemperatureE95 = highTemperatureE95;
    }

    public int getHighTemperatureE98() {
        return highTemperatureE98;
    }

    public void setHighTemperatureE98(int highTemperatureE98) {
        this.highTemperatureE98 = highTemperatureE98;
    }

    public int getHighTemperatureOn() {
        return highTemperatureOn;
    }

    public void setHighTemperatureOn(int highTemperatureOn) {
        this.highTemperatureOn = highTemperatureOn;
    }

    public int getHighTemperatureLpg() {
        return highTemperatureLpg;
    }

    public void setHighTemperatureLpg(int highTemperatureLpg) {
        this.highTemperatureLpg = highTemperatureLpg;
    }

    public int getHightPressureE95() {
        return hightPressureE95;
    }

    public void setHightPressureE95(int hightPressureE95) {
        this.hightPressureE95 = hightPressureE95;
    }

    public int getHightPressureE98() {
        return hightPressureE98;
    }

    public void setHightPressureE98(int hightPressureE98) {
        this.hightPressureE98 = hightPressureE98;
    }

    public int getHightPressureOn() {
        return hightPressureOn;
    }

    public void setHightPressureOn(int hightPressureOn) {
        this.hightPressureOn = hightPressureOn;
    }

    public int getHightPressureLpg() {
        return hightPressureLpg;
    }

    public void setHightPressureLpg(int hightPressureLpg) {
        this.hightPressureLpg = hightPressureLpg;
    }
}
