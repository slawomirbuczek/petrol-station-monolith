package com.pk.petrolstationmonolith.exceptions.carwash;

public class ReservationNotReservedByUserException extends RuntimeException {

    public ReservationNotReservedByUserException(long id) {
        super("Reservation not reserved by user with id: " + id);
    }

}
