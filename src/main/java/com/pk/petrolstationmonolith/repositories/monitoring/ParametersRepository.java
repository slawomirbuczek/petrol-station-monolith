package com.pk.petrolstationmonolith.repositories.monitoring;

import com.pk.petrolstationmonolith.entities.monitoring.Parameters;
import com.pk.petrolstationmonolith.enums.FuelType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ParametersRepository extends JpaRepository<Parameters, Long> {

    Parameters findTopByFuelTypeOrderByDateTimeDesc(FuelType fuelType);

    List<Parameters> findAllByDateTimeBetweenAndFuelType(LocalDateTime dateTime, LocalDateTime dateTime2, FuelType fuelType);

}
