package com.pk.petrolstationmonolith.entities.pricelist;

import com.pk.petrolstationmonolith.enums.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PetrolStationService")
public class PetrolStationService {

    @Id
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Service type cannot be null")
    @Column(name = "ServiceType")
    private ServiceType serviceType;

    @Positive(message = "Price must be postivie number")
    @Column(name = "Price")
    private Float price;

}
