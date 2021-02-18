package com.pk.petrolstationmonolith.repositories.account;

import com.pk.petrolstationmonolith.entities.account.Individual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndividualRepository extends JpaRepository<Individual, Long> {
}
