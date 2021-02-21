package com.pk.petrolstationmonolith.exceptions.carwash;

import java.time.LocalDateTime;

public class ReservationAlreadyTakenException extends RuntimeException {

    public ReservationAlreadyTakenException(LocalDateTime dateTime) {
        super("Reservation for dateTime: " + dateTime + " already taken");
    }

}
