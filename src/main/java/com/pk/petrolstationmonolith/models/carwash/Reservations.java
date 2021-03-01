package com.pk.petrolstationmonolith.models.carwash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservations {

    private List<ResponseReservation> reservations;

}
