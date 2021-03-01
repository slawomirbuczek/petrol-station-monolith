package com.pk.petrolstationmonolith.models.monitoring;


import com.pk.petrolstationmonolith.entities.monitoring.Parameter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Parameters {

    private List<Parameter> parameters;

}
