package com.pk.petrolstationmonolith.exceptions.carwash;

import java.sql.Date;
import java.sql.Time;


public class ReservationNotFoundException extends RuntimeException{

    public ReservationNotFoundException(Date date, Time time) {
        super("Reservation for date: " + date + " " + time + " not found");
    }

}
