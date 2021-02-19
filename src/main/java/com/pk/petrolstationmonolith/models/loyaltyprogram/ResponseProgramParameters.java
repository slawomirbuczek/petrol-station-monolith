package com.pk.petrolstationmonolith.models.loyaltyprogram;

import com.pk.petrolstationmonolith.enums.pricelist.ServiceType;

import java.util.ArrayList;
import java.util.List;

public class ResponseProgramParameters {

    private final List<ServiceParameters> serviceParameters;

    public ResponseProgramParameters() {
        serviceParameters = new ArrayList<>();
        serviceParameters.add(ServiceType.E95.getServiceParameters());
        serviceParameters.add(ServiceType.E98.getServiceParameters());
        serviceParameters.add(ServiceType.ON.getServiceParameters());
        serviceParameters.add(ServiceType.LPG.getServiceParameters());
        serviceParameters.add(ServiceType.WASHING_STANDARD.getServiceParameters());
        serviceParameters.add(ServiceType.WASHING_WITH_WAXING.getServiceParameters());
    }

    public List<ServiceParameters> getServiceParameters() {
        return serviceParameters;
    }

}
