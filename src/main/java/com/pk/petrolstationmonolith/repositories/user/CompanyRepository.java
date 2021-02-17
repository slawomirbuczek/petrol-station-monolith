package com.pk.petrolstationmonolith.repositories.user;

import com.pk.petrolstationmonolith.entities.user.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
