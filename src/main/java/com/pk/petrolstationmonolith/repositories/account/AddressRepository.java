package com.pk.petrolstationmonolith.repositories.account;

import com.pk.petrolstationmonolith.entities.account.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    Optional<Address> findByUserId(Long userId);

}
