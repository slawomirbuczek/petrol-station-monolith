package com.pk.petrolstationmonolith.models.simulations;

import com.pk.petrolstationmonolith.enums.monitoring.AlarmType;
import com.pk.petrolstationmonolith.enums.pricelist.ServiceType;

public class RequestMonitoringSimulation {

    private String email;
    private ServiceType serviceType;
    private AlarmType alarmType;
    private int number;

    public RequestMonitoringSimulation() {
    }

    public RequestMonitoringSimulation(String email, ServiceType serviceType, AlarmType alarmType, int number) {
        this.email = email;
        this.serviceType = serviceType;
        this.alarmType = alarmType;
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public AlarmType getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(AlarmType alarmType) {
        this.alarmType = alarmType;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
