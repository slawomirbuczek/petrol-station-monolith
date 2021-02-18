package com.pk.petrolstationmonolith.exceptions.carwash;

import java.sql.Date;
import java.sql.Time;

public class ReservationAlreadyTakenException extends RuntimeException{

    public ReservationAlreadyTakenException(Date date, Time time) {
        super("Reservation for date: " + date + " " + time + " already taken");
    }

}
