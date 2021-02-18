package com.pk.petrolstationmonolith.repositories.monitoring;

import com.pk.petrolstationmonolith.entities.monitoring.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.sql.Date;
import java.util.List;

@NoRepositoryBean
public interface ParameterRepository<T extends Parameter> extends JpaRepository<T, Long> {

    List<T> findAllByDateBetween(Date from, Date to);

    T findTopByOrderByIdDesc();

}
