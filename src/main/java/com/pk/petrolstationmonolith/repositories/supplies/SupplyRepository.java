package com.pk.petrolstationmonolith.repositories.supplies;

import com.pk.petrolstationmonolith.entities.supplies.Supply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SupplyRepository extends JpaRepository<Supply, Long> {

    List<Supply> findAllByDateAfter(LocalDate date);

    List<Supply> findAllByDateBetween(LocalDate from, LocalDate to);

}
