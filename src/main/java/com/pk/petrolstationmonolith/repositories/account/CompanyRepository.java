package com.pk.petrolstationmonolith.repositories.account;

import com.pk.petrolstationmonolith.entities.account.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findByUserId(long userId);

}
