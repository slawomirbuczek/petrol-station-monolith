package com.pk.petrolstationmonolith.services.carwash;

import com.pk.petrolstationmonolith.dtos.carwash.ReservationDto;
import com.pk.petrolstationmonolith.entities.account.User;
import com.pk.petrolstationmonolith.entities.carwash.Reservation;
import com.pk.petrolstationmonolith.exceptions.carwash.ReservationAlreadyTakenException;
import com.pk.petrolstationmonolith.exceptions.carwash.ReservationNotFoundException;
import com.pk.petrolstationmonolith.exceptions.carwash.ReservationNotReservedByUserException;
import com.pk.petrolstationmonolith.exceptions.carwash.WrongReservationDateException;
import com.pk.petrolstationmonolith.models.carwash.RequestCancelReservation;
import com.pk.petrolstationmonolith.models.carwash.RequestReservationDate;
import com.pk.petrolstationmonolith.models.carwash.RequestReserve;
import com.pk.petrolstationmonolith.models.carwash.ReservationsList;
import com.pk.petrolstationmonolith.repositories.account.UserRepository;
import com.pk.petrolstationmonolith.repositories.carwash.ReservationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;

    public ReservationService(ReservationRepository reservationRepository, UserRepository userRepository) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
    }

    public ReservationsList getReservations(RequestReservationDate request) {
        if (request.getDate().toLocalDate().isBefore(new Date(System.currentTimeMillis()).toLocalDate())
                || request.getDate().toLocalDate().isAfter(LocalDate.now().plusDays(14))) {
            throw new WrongReservationDateException();
        }

        List<Reservation> reservations = reservationRepository.findAllByDate(request.getDate());

        if (reservations.isEmpty()) {
            return createEmptyReservationsForDate(request.getDate());
        }

        return new ReservationsList(
                reservations.stream().map(this::mapReservationtoDTO).collect(Collectors.toList())
        );
    }

    private ReservationsList createEmptyReservationsForDate(Date date) {
        List<Reservation> reservations = new ArrayList<>();

        long interval = 30 * 60 * 1000L;

        Time time = Time.valueOf("06:00:00");
        for (int i = 0; i < 34; i++) {
            reservations.add(reservationRepository.save(createNewReservation(
                    date,
                    new Time(time.getTime() + i * interval)
            )));

        }
        return new ReservationsList(
                reservations.stream().map(this::mapReservationtoDTO).collect(Collectors.toList())
        );
    }

    private Reservation createNewReservation(Date date, Time time) {
        Reservation reservation = new Reservation();
        reservation.setDate(date);
        reservation.setTime(time);
        return reservation;
    }

    public ReservationDto reserve(RequestReserve request) {
        Reservation reservation = reservationRepository.findByDateAndTime(request.getDate(), request.getTime())
                .orElseThrow(() -> new ReservationNotFoundException(request.getDate(), request.getTime()));

        if (Objects.nonNull(reservation.getUser())) {
            throw new ReservationAlreadyTakenException(request.getDate(), request.getTime());
        }

        reservation.setUser(getUserFromAuth());
        reservation.setWashingType(request.getWashingType());

        return mapReservationtoDTO(reservationRepository.save(reservation));
    }

    public ReservationDto cancelReservation(RequestCancelReservation request) {
        Reservation reservation = reservationRepository.findByDateAndTime(request.getDate(), request.getTime())
                .orElseThrow(() -> new ReservationNotFoundException(request.getDate(), request.getTime()));

        Long userId = getUserFromAuth().getId();
        if (Objects.isNull(reservation.getUser()) || !Objects.equals(reservation.getUser().getId(), userId)) {
            throw new ReservationNotReservedByUserException(userId);
        }

        reservation.setWashingType(null);
        reservation.setUser(null);
        return mapReservationtoDTO(reservationRepository.save(reservation));
    }

    private ReservationDto mapReservationtoDTO(Reservation reservation) {
        ReservationDto reservationDto = new ModelMapper().map(reservation, ReservationDto.class);
        if (Objects.nonNull(reservation.getUser())) {
            reservationDto.setUserId(reservation.getUser().getId());
        }
        return reservationDto;
    }

    private User getUserFromAuth() {
        long id = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());
        return userRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User with id: " + id + " not found.")
        );
    }

}
