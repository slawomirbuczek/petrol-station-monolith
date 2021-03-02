package com.pk.petrolstationmonolith.repositories.transactions;

import com.pk.petrolstationmonolith.entities.transactions.Transaction;
import com.pk.petrolstationmonolith.enums.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllByUserId(Long userId);

    @Query("SELECT SUM(number) FROM Transaction WHERE serviceType = ?1 AND dateTime >= ?2 AND dateTime <= ?3")
    Optional<Long> sumNumberByServiceTypeAndDateTimeBetween(ServiceType serviceType, LocalDateTime from, LocalDateTime to);

    @Query("SELECT SUM(cost) FROM Transaction WHERE serviceType = ?1 AND dateTime >= ?2 AND dateTime <= ?3")
    Optional<Double> profitByServiceTypeAndDateTimeBetween(ServiceType serviceType, LocalDateTime from, LocalDateTime to);

}
