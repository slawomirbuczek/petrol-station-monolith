package com.pk.petrolstationmonolith.models.monitoring;

import com.pk.petrolstationmonolith.dtos.monitoring.ParameterDto;

import java.util.List;

public class Parameters {

    List<ParameterDto> e95s;
    List<ParameterDto> e98s;
    List<ParameterDto> ons;
    List<ParameterDto> lpgs;

    public Parameters() {
    }

    public Parameters(List<ParameterDto> e95s, List<ParameterDto> e98s, List<ParameterDto> ons, List<ParameterDto> lpgs) {
        this.e95s = e95s;
        this.e98s = e98s;
        this.ons = ons;
        this.lpgs = lpgs;
    }

    public List<ParameterDto> getE95s() {
        return e95s;
    }

    public void setE95s(List<ParameterDto> e95s) {
        this.e95s = e95s;
    }

    public List<ParameterDto> getE98s() {
        return e98s;
    }

    public void setE98s(List<ParameterDto> e98s) {
        this.e98s = e98s;
    }

    public List<ParameterDto> getOns() {
        return ons;
    }

    public void setOns(List<ParameterDto> ons) {
        this.ons = ons;
    }

    public List<ParameterDto> getLpgs() {
        return lpgs;
    }

    public void setLpgs(List<ParameterDto> lpgs) {
        this.lpgs = lpgs;
    }
}
