package com.pk.petrolstationmonolith.models.monitoring;


import com.pk.petrolstationmonolith.entities.monitoring.Parameter;

import java.util.List;

public class Parameters {

    private List<Parameter> parameters;

    public Parameters() {
    }

    public Parameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }

}
