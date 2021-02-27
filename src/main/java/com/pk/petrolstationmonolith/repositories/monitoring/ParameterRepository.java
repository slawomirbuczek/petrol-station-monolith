package com.pk.petrolstationmonolith.repositories.monitoring;

import com.pk.petrolstationmonolith.entities.monitoring.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ParameterRepository extends JpaRepository<Parameter, Long> {

    List<Parameter> findAllByDateTimeBetween(LocalDateTime from, LocalDateTime to);

    Parameter findTopByOrderByDateTimeDesc();

}
