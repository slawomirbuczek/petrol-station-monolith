package com.pk.petrolstationmonolith.dtos.account;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IndividualDto {

    private String firstName;

    private String lastName;

    private String pesel;

    private String nip;

}
