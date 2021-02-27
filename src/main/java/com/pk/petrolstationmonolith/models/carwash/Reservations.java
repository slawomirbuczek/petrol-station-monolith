package com.pk.petrolstationmonolith.models.carwash;

import java.util.List;

public class Reservations {

    private List<ResponseReservation> reservations;

    public Reservations() {
    }

    public Reservations(List<ResponseReservation> reservations) {
        this.reservations = reservations;
    }

    public List<ResponseReservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<ResponseReservation> reservations) {
        this.reservations = reservations;
    }

}
