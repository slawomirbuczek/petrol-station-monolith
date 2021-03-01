package com.pk.petrolstationmonolith.controllers.carwash;

import com.pk.petrolstationmonolith.models.RequestDateTime;
import com.pk.petrolstationmonolith.models.carwash.ReservationDetails;
import com.pk.petrolstationmonolith.models.carwash.Reservations;
import com.pk.petrolstationmonolith.models.carwash.ResponseReservation;
import com.pk.petrolstationmonolith.services.carwash.ReservationService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger logger = LoggerFactory.getLogger(ReservationController.class);
    private final ReservationService reservationService;

    @GetMapping("/{dateTime}")
    @PreAuthorize("hasAnyRole('CAR_WASH','ADMIN','OWNER')")
    public ResponseEntity<ReservationDetails> getReservationDetails(@PathVariable LocalDateTime dateTime) {
        logger.trace("Get reservation details method invoked with dateTime " + dateTime.toString());
        return ResponseEntity.ok(reservationService.getReservationDetails(dateTime));
    }

    @GetMapping
    public ResponseEntity<Reservations> getReservations(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> optionalDate) {
        logger.trace("Get reservations method invoked with optionalDate " + optionalDate.toString());
        return ResponseEntity.ok(reservationService.getReservations(optionalDate));
    }

    @GetMapping("/history")
    public ResponseEntity<Reservations> getUserReservations(Principal principal) {
        return ResponseEntity.ok(reservationService.getUserReservation(Long.parseLong(principal.getName())));
    }

    @PostMapping
    public ResponseEntity<ResponseReservation> reserve(@Valid @RequestBody RequestDateTime request, Principal principal) {
        logger.trace("Reserve method invoked with date " + request.getDateTime().toString() + " and user " + principal.getName());
        return ResponseEntity.ok(reservationService.reserve(request, Long.parseLong(principal.getName())));
    }

    @DeleteMapping
    public ResponseEntity<ResponseReservation> cancelReseravation(@Valid @RequestBody RequestDateTime request,
                                                                  Principal principal) {
        logger.trace("Cancel reseravation method invoked with date " + request.getDateTime().toString() + " and user " + principal.getName());
        return ResponseEntity.ok(reservationService.cancelReservation(request, Long.parseLong(principal.getName())));
    }

}
