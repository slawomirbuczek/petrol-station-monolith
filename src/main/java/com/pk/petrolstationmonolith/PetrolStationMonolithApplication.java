package com.pk.petrolstationmonolith;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class PetrolStationMonolithApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetrolStationMonolithApplication.class, args);
    }

}
