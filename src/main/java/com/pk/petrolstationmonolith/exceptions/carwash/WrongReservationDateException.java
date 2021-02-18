package com.pk.petrolstationmonolith.exceptions.carwash;

public class WrongReservationDateException extends RuntimeException{

    public WrongReservationDateException() {
        super("Wrong reservation date");
    }

}
