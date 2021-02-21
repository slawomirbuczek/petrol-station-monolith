package com.pk.petrolstationmonolith.repositories.transactions;

import com.pk.petrolstationmonolith.entities.account.User;
import com.pk.petrolstationmonolith.entities.transactions.Transaction;
import com.pk.petrolstationmonolith.enums.pricelist.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllByUser(User user);

    @Query("SELECT SUM(number) FROM Transaction WHERE serviceType = ?1 AND date >= ?2 AND date <= ?3")
    Optional<Long> sumNumberByServiceTypeAndDateBetween(ServiceType serviceType, Date from, Date to);

    @Query("SELECT SUM(cost) FROM Transaction WHERE serviceType = ?1 AND date >= ?2 AND date <= ?3")
    Optional<Double> sumProfitByServiceTypeAndDateBetween(ServiceType serviceType, Date from, Date to);

}
