package com.pk.petrolstationmonolith.repositories;

import com.pk.petrolstationmonolith.entities.user.Individual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndividualRepository extends JpaRepository<Individual, Long> {
}
