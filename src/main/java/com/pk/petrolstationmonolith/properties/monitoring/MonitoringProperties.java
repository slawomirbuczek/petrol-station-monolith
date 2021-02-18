package com.pk.petrolstationmonolith.properties.monitoring;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("monitoring")
public class MonitoringProperties {

    /**
     * Interval between monitoring in minutes.
     */
    private int interval;

    public int getInterval() {
        return interval * 60 * 1000;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

}
