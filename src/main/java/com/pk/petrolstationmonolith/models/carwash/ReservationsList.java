package com.pk.petrolstationmonolith.models.carwash;

import com.pk.petrolstationmonolith.dtos.carwash.ReservationDto;

import java.util.List;

public class ReservationsList {

    private List<ReservationDto> reservations;

    public ReservationsList() {
    }

    public ReservationsList(List<ReservationDto> reservations) {
        this.reservations = reservations;
    }

    public List<ReservationDto> getTimetable() {
        return reservations;
    }

    public void setTimetable(List<ReservationDto> reservations) {
        this.reservations = reservations;
    }

}
