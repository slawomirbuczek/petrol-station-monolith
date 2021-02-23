package com.pk.petrolstationmonolith.services.carwash;

import com.pk.petrolstationmonolith.dtos.carwash.ReservationDto;
import com.pk.petrolstationmonolith.entities.carwash.Reservation;
import com.pk.petrolstationmonolith.exceptions.carwash.ReservationAlreadyTakenException;
import com.pk.petrolstationmonolith.exceptions.carwash.ReservationNotFoundException;
import com.pk.petrolstationmonolith.exceptions.carwash.ReservationNotReservedByUserException;
import com.pk.petrolstationmonolith.exceptions.carwash.WrongReservationDateException;
import com.pk.petrolstationmonolith.models.carwash.RequestCancelReservation;
import com.pk.petrolstationmonolith.models.carwash.RequestReservationDate;
import com.pk.petrolstationmonolith.models.carwash.RequestReserve;
import com.pk.petrolstationmonolith.models.carwash.ResponseReservations;
import com.pk.petrolstationmonolith.repositories.carwash.ReservationRepository;
import com.pk.petrolstationmonolith.services.account.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserService userService;

    public ReservationService(ReservationRepository reservationRepository, UserService userService) {
        this.reservationRepository = reservationRepository;
        this.userService = userService;
    }

    public ResponseReservations getReservations(RequestReservationDate request) {

        if (request.getDate().isBefore(LocalDate.now())
                || request.getDate().isAfter(LocalDate.now().plusDays(14))) {
            throw new WrongReservationDateException();
        }

        List<Reservation> reservations = reservationRepository
                .findAllByDateTimeBetween(request.getDate().atStartOfDay(), request.getDate().plusDays(1).atStartOfDay());

        if (reservations.isEmpty()) {
            return createEmptyReservationsForDate(request.getDate());
        }

        return new ResponseReservations(
                reservations.stream().map(this::mapReservationtoDTO).collect(Collectors.toList())
        );

    }

    public ReservationDto reserve(RequestReserve request, Principal principal) {
        Reservation reservation = reservationRepository.findByDateTime(request.getDateTime())
                .orElseThrow(() -> new ReservationNotFoundException((request.getDateTime())));

        if (Objects.nonNull(reservation.getUser())) {
            throw new ReservationAlreadyTakenException(request.getDateTime());
        }

        reservation.setUser(userService.getUser(Long.parseLong(principal.getName())));
        reservation.setWashingType(request.getWashingType());

        return mapReservationtoDTO(reservationRepository.save(reservation));
    }

    public ReservationDto cancelReservation(RequestCancelReservation request, Principal principal) {
        Reservation reservation = reservationRepository.findByDateTime(request.getDateTime())
                .orElseThrow(() -> new ReservationNotFoundException(request.getDateTime()));

        Long userId = userService.getUser(Long.parseLong(principal.getName())).getId();
        if (Objects.isNull(reservation.getUser()) || !Objects.equals(reservation.getUser().getId(), userId)) {
            throw new ReservationNotReservedByUserException(userId);
        }

        reservation.setWashingType(null);
        reservation.setUser(null);
        return mapReservationtoDTO(reservationRepository.save(reservation));
    }

    private ResponseReservations createEmptyReservationsForDate(LocalDate date) {
        List<Reservation> reservations = new ArrayList<>();

        for (int i = 0; i < 34; i++) {
            reservations.add(reservationRepository.save(createNewReservation(
                    LocalDateTime.of(date, LocalTime.of(6, 0).plusMinutes(i * 30))
            )));
        }

        return new ResponseReservations(
                reservations.stream().map(this::mapReservationtoDTO).collect(Collectors.toList())
        );
    }

    private Reservation createNewReservation(LocalDateTime dateTime) {
        Reservation reservation = new Reservation();
        reservation.setDateTime(dateTime);
        return reservation;
    }

    private ReservationDto mapReservationtoDTO(Reservation reservation) {
        ReservationDto reservationDto = new ModelMapper().map(reservation, ReservationDto.class);
        if (Objects.nonNull(reservation.getUser())) {
            reservationDto.setUserId(reservation.getUser().getId());
        }
        return reservationDto;
    }

}
