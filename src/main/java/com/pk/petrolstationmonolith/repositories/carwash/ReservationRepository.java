package com.pk.petrolstationmonolith.repositories.carwash;

import com.pk.petrolstationmonolith.entities.carwash.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findAllByDate(Date date);

    Optional<Reservation> findByDateAndTime(Date date, Time time);
}
