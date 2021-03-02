package com.pk.petrolstationmonolith.models.simulations;

import com.pk.petrolstationmonolith.enums.ServiceType;
import com.pk.petrolstationmonolith.enums.AlarmType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestMonitoringAlarm {

    @Email(message = "Invalid email")
    private String email;

    @NotNull(message = "Service type cannot be null")
    private ServiceType serviceType;

    @NotNull(message = "Alert type cannot be null")
    private AlarmType alarmType;

    @Positive(message = "Tank number must be positive")
    private int tankNumber;

    @NotNull(message = "Value cannot be null")
    private int value;

}
