package com.pk.petrolstationmonolith.models.carwash;

import com.pk.petrolstationmonolith.dtos.carwash.ReservationDto;

import java.util.List;

public class ResponseReservations {

    private List<ReservationDto> reservations;

    public ResponseReservations() {
    }

    public ResponseReservations(List<ReservationDto> reservations) {
        this.reservations = reservations;
    }

    public List<ReservationDto> getTimetable() {
        return reservations;
    }

    public void setTimetable(List<ReservationDto> reservations) {
        this.reservations = reservations;
    }

}
