package com.pk.petrolstationmonolith.services.carwash;

import com.pk.petrolstationmonolith.dtos.account.IndividualDto;
import com.pk.petrolstationmonolith.entities.carwash.Bookings;
import com.pk.petrolstationmonolith.enums.Roles;
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
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@AllArgsConstructor
@Slf4j
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserService userService;
    private final IndividualService individualService;
    private final CompanyService companyService;

    public ReservationDetails getReservationDetails(LocalDateTime dateTime) {
        Bookings bookings = getReservation(dateTime);
        long userId = bookings.getCustomers().getId();
        String name;
        log.trace("Getting car wash reservation details with date " + dateTime.toString());
        if (bookings.getCustomers().getRole() == Roles.USER_COMPANY) {
            name = companyService.getCompanyDto(userId).getName();
        } else {
            IndividualDto individualDto = individualService.getIndividualDto(userId);
            name = individualDto.getFirstName() + " " + individualDto.getLastName();
        }

        return new ReservationDetails(dateTime, userId, name);
    }

    public Reservations getUserReservations(long userId) {
        log.trace("Getting car wash reservations for user with id " + userId);
        return mapListOfReservationsToReservations(reservationRepository.findAllByUserId(userId));
    }

    public Reservations getReservations(Optional<LocalDate> optionalDate) {
        LocalDate date = optionalDate.orElseGet(LocalDate::now);
        log.trace("Getting car wash reservations for date " + date.toString());
        if (date.isBefore(LocalDate.now()) || date.isAfter(LocalDate.now().plusDays(14))) {
            throw new WrongReservationDateException();
        }

        List<Bookings> bookings = reservationRepository
                .findAllByDateTimeBetween(date.atStartOfDay(), date.plusDays(1).atStartOfDay());

        if (bookings.isEmpty()) {
            return createEmptyReservationsForDate(date);
        }

        return mapListOfReservationsToReservations(bookings);

    }

    public ResponseReservation reserve(RequestDateTime request, long userId) {
        Bookings bookings = getReservation(request.getDateTime());
        log.trace("Reserving car wash with date " + bookings.getDateTime() + " for user with id " + userId);
        if (Objects.nonNull(bookings.getCustomers())) {
            throw new ReservationAlreadyTakenException(request.getDateTime());
        }

        bookings.setCustomers(userService.getUser(userId));
        return mapReservationToResponse(reservationRepository.save(bookings));
    }

    public ResponseReservation cancelReservation(RequestDateTime request, long userId) {
        Bookings bookings = getReservation(request.getDateTime());
        log.trace("Canceling car wash reservation with date " + bookings.getDateTime() + " for user with id " + userId);
        if (Objects.isNull(bookings.getCustomers()) || !Objects.equals(bookings.getCustomers().getId(), userId)) {
            throw new ReservationNotReservedByUserException(userId);
        }

        bookings.setCustomers(null);
        return mapReservationToResponse(reservationRepository.save(bookings));
    }

    private Bookings getReservation(LocalDateTime dateTime) {
        return reservationRepository.findByDateTime(dateTime)
                .orElseThrow(() -> new ReservationNotFoundException(dateTime));
    }

    private Reservations createEmptyReservationsForDate(LocalDate date) {
        List<Bookings> bookings = new ArrayList<>();

        for (int i = 0; i < 34; i++) {
            bookings.add(reservationRepository.save(createNewReservation(
                    LocalDateTime.of(date, LocalTime.of(6, 0).plusMinutes(i * 30))
            )));
        }

        return mapListOfReservationsToReservations(bookings);
    }

    private Bookings createNewReservation(LocalDateTime dateTime) {
        Bookings bookings = new Bookings();
        bookings.setDateTime(dateTime);
        return bookings;
    }

    private ResponseReservation mapReservationToResponse(Bookings bookings) {
        ResponseReservation responseReservation = new ResponseReservation();
        responseReservation.setDateTime(bookings.getDateTime());
        responseReservation.setAvailable(Objects.isNull(bookings.getCustomers()));
        return responseReservation;
    }

    private Reservations mapListOfReservationsToReservations(List<Bookings> bookings) {
        return new Reservations(
                bookings.stream().map(this::mapReservationToResponse).collect(Collectors.toList())
        );
    }
}
