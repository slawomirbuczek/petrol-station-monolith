package com.pk.petrolstationmonolith.models.carwash;

import com.pk.petrolstationmonolith.dtos.carwash.ReservationDto;

import java.util.List;

public class ResponseReservationsList {

    private List<ReservationDto> reservations;

    public ResponseReservationsList() {
    }

    public ResponseReservationsList(List<ReservationDto> reservations) {
        this.reservations = reservations;
    }

    public List<ReservationDto> getTimetable() {
        return reservations;
    }

    public void setTimetable(List<ReservationDto> reservations) {
        this.reservations = reservations;
    }

}
