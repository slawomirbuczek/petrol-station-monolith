package com.pk.petrolstationmonolith.dtos.transactions;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pk.petrolstationmonolith.enums.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TransactionDto {

    private Long id;

    private ServiceType serviceType;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTime;

    private int number;

    private double cost;

}
