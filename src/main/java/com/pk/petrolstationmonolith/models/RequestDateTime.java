package com.pk.petrolstationmonolith.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestDateTime {

    @NotNull(message = "Date cannot be null")
    private LocalDateTime dateTime;

}
