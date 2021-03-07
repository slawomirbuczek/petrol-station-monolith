package com.pk.petrolstationmonolith.models.monitoring;

import com.pk.petrolstationmonolith.dtos.monitoring.ParametersDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ListOfParameters {

    List<ParametersDto> parametersDtos;

}
