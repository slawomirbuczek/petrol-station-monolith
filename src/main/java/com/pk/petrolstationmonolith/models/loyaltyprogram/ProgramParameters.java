package com.pk.petrolstationmonolith.models.loyaltyprogram;

import com.pk.petrolstationmonolith.enums.ServiceType;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ProgramParameters {

    private final List<ServiceParameters> serviceParameters;

    public ProgramParameters() {
        serviceParameters = new ArrayList<>();
        serviceParameters.add(ServiceType.E95.getServiceParameters());
        serviceParameters.add(ServiceType.E98.getServiceParameters());
        serviceParameters.add(ServiceType.ON.getServiceParameters());
        serviceParameters.add(ServiceType.LPG.getServiceParameters());
        serviceParameters.add(ServiceType.WASHING_STANDARD.getServiceParameters());
        serviceParameters.add(ServiceType.WASHING_WAXING.getServiceParameters());
    }

}
