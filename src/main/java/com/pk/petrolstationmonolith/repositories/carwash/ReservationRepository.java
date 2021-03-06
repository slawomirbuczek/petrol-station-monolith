package com.pk.petrolstationmonolith.repositories.carwash;

import com.pk.petrolstationmonolith.entities.carwash.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Bookings, Long> {

    List<Bookings> findAllByDateTimeBetween(LocalDateTime from, LocalDateTime to);

    Optional<Bookings> findByDateTime(LocalDateTime dateTime);

    List<Bookings> findAllByUserId(Long userId);

}
