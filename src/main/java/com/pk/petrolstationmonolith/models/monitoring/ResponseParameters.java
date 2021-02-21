package com.pk.petrolstationmonolith.models.monitoring;

import com.pk.petrolstationmonolith.dtos.monitoring.ParameterDto;

import java.util.List;

public class ResponseParameters {

    private List<ParameterDto> parameters;

    public ResponseParameters() {
    }

    public ResponseParameters(List<ParameterDto> parameters) {
        this.parameters = parameters;
    }

    public List<ParameterDto> getParameters() {
        return parameters;
    }

    public void setParameters(List<ParameterDto> parameters) {
        this.parameters = parameters;
    }

}
