package com.pk.petrolstationmonolith.entities.pricelist;

import com.pk.petrolstationmonolith.enums.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "price_list")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetrolStationService {

    @Id
    @Enumerated(EnumType.STRING)
    private ServiceType serviceType;

    @Positive
    private float price;

}
