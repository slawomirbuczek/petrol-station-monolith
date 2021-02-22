package com.pk.petrolstationmonolith;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class PetrolStationMonolithApplication {

    private static final Logger logger = LoggerFactory.getLogger(PetrolStationMonolithApplication.class);

    public static void main(String[] args) {
        logger.trace("Application attempting to start");
        SpringApplication.run(PetrolStationMonolithApplication.class, args);
        logger.trace("Application started");
    }

}
