package com.pk.petrolstationmonolith.repositories.loyaltyprogram;

import com.pk.petrolstationmonolith.entities.account.User;
import com.pk.petrolstationmonolith.entities.loyaltyprogram.LoyaltyProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoyaltyProgramRepository extends JpaRepository<LoyaltyProgram, Long> {

    Optional<LoyaltyProgram> findByUserId(Long id);

}
