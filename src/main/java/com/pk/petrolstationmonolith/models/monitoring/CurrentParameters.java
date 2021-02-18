package com.pk.petrolstationmonolith.models.monitoring;

import com.pk.petrolstationmonolith.dtos.monitoring.ParameterDto;

public class CurrentParameters {

    private ParameterDto e95;
    private ParameterDto e98;
    private ParameterDto on;
    private ParameterDto lpg;

    public CurrentParameters() {
    }

    public CurrentParameters(ParameterDto e95, ParameterDto e98, ParameterDto on, ParameterDto lpg) {
        this.e95 = e95;
        this.e98 = e98;
        this.on = on;
        this.lpg = lpg;
    }

    public ParameterDto getE95() {
        return e95;
    }

    public void setE95(ParameterDto e95) {
        this.e95 = e95;
    }

    public ParameterDto getE98() {
        return e98;
    }

    public void setE98(ParameterDto e98) {
        this.e98 = e98;
    }

    public ParameterDto getOn() {
        return on;
    }

    public void setOn(ParameterDto on) {
        this.on = on;
    }

    public ParameterDto getLpg() {
        return lpg;
    }

    public void setLpg(ParameterDto lpg) {
        this.lpg = lpg;
    }

}
