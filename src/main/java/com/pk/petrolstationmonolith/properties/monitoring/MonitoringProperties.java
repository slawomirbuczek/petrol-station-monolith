package com.pk.petrolstationmonolith.properties.monitoring;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

@Data
@ConfigurationProperties("monitoring")
public class MonitoringProperties {

    /**
     * Interval between monitoring in minutes.
     */
    private int interval;

    @Bean
    @ConfigurationProperties("monitoring.e95")
    public ParameterProperites e95Properties() {
        return new ParameterProperites();
    }

    @Bean
    @ConfigurationProperties("monitoring.e98")
    public ParameterProperites e98Properties() {
        return new ParameterProperites();
    }

    @Bean
    @ConfigurationProperties("monitoring.on")
    public ParameterProperites onProperties() {
        return new ParameterProperites();
    }

    @Bean
    @ConfigurationProperties("monitoring.lpg")
    public ParameterProperites lpgProperties() {
        return new ParameterProperites();
    }

    public int getInterval() {
        return interval * 60_000;
    }
}
