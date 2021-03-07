package com.pk.petrolstationmonolith.exceptions.carwash;

public class ReservationNotReservedByCustomerException extends RuntimeException {

    public ReservationNotReservedByCustomerException(long id) {
        super("Reservation not reserved by customer with id: " + id);
    }

}
