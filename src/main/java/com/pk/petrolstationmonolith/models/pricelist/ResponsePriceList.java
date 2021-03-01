package com.pk.petrolstationmonolith.models.pricelist;

import com.pk.petrolstationmonolith.entities.pricelist.PetrolStationService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponsePriceList {

    private List<PetrolStationService> priceList;

}
