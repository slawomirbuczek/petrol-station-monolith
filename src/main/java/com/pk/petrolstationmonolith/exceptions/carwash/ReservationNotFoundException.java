package com.pk.petrolstationmonolith.exceptions.carwash;

import java.time.LocalDateTime;


public class ReservationNotFoundException extends RuntimeException{

    public ReservationNotFoundException(LocalDateTime dateTime) {
        super("Reservation for date: " + dateTime + " not found");
    }

}
