package com.pk.petrolstationmonolith.controllers.carwash;

import com.pk.petrolstationmonolith.models.RequestDateTime;
import com.pk.petrolstationmonolith.models.carwash.ReservationDetails;
import com.pk.petrolstationmonolith.models.carwash.Reservations;
import com.pk.petrolstationmonolith.models.carwash.ResponseReservation;
import com.pk.petrolstationmonolith.services.carwash.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/carwash/reservations")
@AllArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping("/{dateTime}")
    @PreAuthorize("hasAnyRole('CAR_WASH','ADMIN','OWNER')")
    public ResponseEntity<ReservationDetails> getReservationDetails(@PathVariable LocalDateTime dateTime) {
        return ResponseEntity.ok(reservationService.getReservationDetails(dateTime));
    }

    @GetMapping
    public ResponseEntity<Reservations> getReservations(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> optionalDate) {
        return ResponseEntity.ok(reservationService.getReservations(optionalDate));
    }

    @GetMapping("/history")
    public ResponseEntity<Reservations> getCustomerReservations(Principal principal) {
        return ResponseEntity.ok(reservationService.getCustomerReservations(Long.parseLong(principal.getName())));
    }

    @PostMapping
    public ResponseEntity<ResponseReservation> reserve(@Valid @RequestBody RequestDateTime request, Principal principal) {
        return ResponseEntity.ok(reservationService.reserve(request, Long.parseLong(principal.getName())));
    }

    @DeleteMapping
    public ResponseEntity<ResponseReservation> cancelReseravation(@Valid @RequestBody RequestDateTime request, Principal principal) {
        return ResponseEntity.ok(reservationService.cancelReservation(request, Long.parseLong(principal.getName())));
    }

}
