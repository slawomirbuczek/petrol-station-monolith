package com.pk.petrolstationmonolith.entities.carwash;

import com.pk.petrolstationmonolith.entities.account.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservation")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Id
    private LocalDateTime dateTime;

    @ManyToOne
    private User user;

}
