package com.pk.petrolstationmonolith.controllers.carwash;

import com.pk.petrolstationmonolith.dtos.carwash.ReservationDto;
import com.pk.petrolstationmonolith.models.carwash.RequestCancelReservation;
import com.pk.petrolstationmonolith.models.carwash.RequestReservationDate;
import com.pk.petrolstationmonolith.models.carwash.RequestReserve;
import com.pk.petrolstationmonolith.models.carwash.ResponseReservations;
import com.pk.petrolstationmonolith.services.carwash.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/carwash/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<ResponseReservations> getReservation(@Valid @RequestBody RequestReservationDate request) {
        return ResponseEntity.ok(reservationService.getReservations(request));
    }

    @PostMapping("/reservation")
    public ResponseEntity<ReservationDto> reserve(@Valid @RequestBody RequestReserve request) {
        return ResponseEntity.ok(reservationService.reserve(request));
    }

    @DeleteMapping("/reservation")
    public ResponseEntity<ReservationDto> cancelReseravation(@Valid @RequestBody RequestCancelReservation request) {
        return ResponseEntity.ok(reservationService.cancelReservation(request));
    }

}
