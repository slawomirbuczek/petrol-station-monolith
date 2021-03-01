package com.pk.petrolstationmonolith.models.supplies;

import com.pk.petrolstationmonolith.entities.supplies.Supply;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Supplies {

    private List<Supply> supplies;

}
