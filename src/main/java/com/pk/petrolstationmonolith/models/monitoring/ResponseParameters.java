package com.pk.petrolstationmonolith.models.monitoring;


import com.pk.petrolstationmonolith.entities.monitoring.Monitoring;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseParameters {

    private List<Monitoring> parameters;

}
