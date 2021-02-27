package com.pk.petrolstationmonolith.services.carwash;

import com.pk.petrolstationmonolith.dtos.account.IndividualDto;
import com.pk.petrolstationmonolith.entities.carwash.Reservation;
import com.pk.petrolstationmonolith.enums.account.UserType;
import com.pk.petrolstationmonolith.exceptions.carwash.ReservationAlreadyTakenException;
import com.pk.petrolstationmonolith.exceptions.carwash.ReservationNotFoundException;
import com.pk.petrolstationmonolith.exceptions.carwash.ReservationNotReservedByUserException;
import com.pk.petrolstationmonolith.exceptions.carwash.WrongReservationDateException;
import com.pk.petrolstationmonolith.models.RequestDateTime;
import com.pk.petrolstationmonolith.models.carwash.ReservationDetails;
import com.pk.petrolstationmonolith.models.carwash.Reservations;
import com.pk.petrolstationmonolith.models.carwash.ResponseReservation;
import com.pk.petrolstationmonolith.repositories.carwash.ReservationRepository;
import com.pk.petrolstationmonolith.services.account.CompanyService;
import com.pk.petrolstationmonolith.services.account.IndividualService;
import com.pk.petrolstationmonolith.services.account.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserService userService;
    private final IndividualService individualService;
    private final CompanyService companyService;

    public ReservationService(ReservationRepository reservationRepository, UserService userService,
                              IndividualService individualService, CompanyService companyService) {
        this.reservationRepository = reservationRepository;
        this.userService = userService;
        this.individualService = individualService;
        this.companyService = companyService;
    }

    public ReservationDetails getReservationDetails(LocalDateTime dateTime) {
        Reservation reservation = getReservation(dateTime);
        long userId = reservation.getUser().getId();
        String name;

        if (reservation.getUser().getUserType() == UserType.COMPANY) {
            name = companyService.getCompanyDto(userId).getName();
        } else {
            IndividualDto individualDto = individualService.getIndividualDto(userId);
            name = individualDto.getFirstName() + " " + individualDto.getLastName();
        }

        return new ReservationDetails(dateTime, userId, name);
    }

    public Reservations getReservations(Optional<LocalDate> optionalDate) {
        LocalDate date = optionalDate.orElseGet(LocalDate::now);

        if (date.isBefore(LocalDate.now()) || date.isAfter(LocalDate.now().plusDays(14))) {
            throw new WrongReservationDateException();
        }

        List<Reservation> reservations = reservationRepository
                .findAllByDateTimeBetween(date.atStartOfDay(), date.plusDays(1).atStartOfDay());

        if (reservations.isEmpty()) {
            return createEmptyReservationsForDate(date);
        }

        return new Reservations(
                reservations.stream().map(this::mapReservationToResponse).collect(Collectors.toList())
        );

    }

    public ResponseReservation reserve(RequestDateTime request, long userId) {
        Reservation reservation = getReservation(request.getDateTime());

        if (Objects.nonNull(reservation.getUser())) {
            throw new ReservationAlreadyTakenException(request.getDateTime());
        }

        reservation.setUser(userService.getUser(userId));

        return mapReservationToResponse(reservationRepository.save(reservation));
    }

    public ResponseReservation cancelReservation(RequestDateTime request, long userId) {
        Reservation reservation = getReservation(request.getDateTime());

        if (Objects.isNull(reservation.getUser()) || !Objects.equals(reservation.getUser().getId(), userId)) {
            throw new ReservationNotReservedByUserException(userId);
        }

        reservation.setUser(null);

        return mapReservationToResponse(reservationRepository.save(reservation));
    }

    private Reservation getReservation(LocalDateTime dateTime) {
        return reservationRepository.findByDateTime(dateTime)
                .orElseThrow(() -> new ReservationNotFoundException(dateTime));
    }

    private Reservations createEmptyReservationsForDate(LocalDate date) {
        List<Reservation> reservations = new ArrayList<>();

        for (int i = 0; i < 34; i++) {
            reservations.add(reservationRepository.save(createNewReservation(
                    LocalDateTime.of(date, LocalTime.of(6, 0).plusMinutes(i * 30))
            )));
        }

        return new Reservations(
                reservations.stream().map(this::mapReservationToResponse).collect(Collectors.toList())
        );
    }

    private Reservation createNewReservation(LocalDateTime dateTime) {
        Reservation reservation = new Reservation();
        reservation.setDateTime(dateTime);
        return reservation;
    }

    private ResponseReservation mapReservationToResponse(Reservation reservation) {
        ResponseReservation responseReservation = new ResponseReservation();
        responseReservation.setDateTime(reservation.getDateTime());
        responseReservation.setAvailable(Objects.isNull(reservation.getUser()));
        return responseReservation;
    }

}
