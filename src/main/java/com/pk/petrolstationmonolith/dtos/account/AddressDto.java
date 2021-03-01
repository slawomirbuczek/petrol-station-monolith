package com.pk.petrolstationmonolith.dtos.account;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressDto {

    private String country;

    private String city;

    private String zip;

    private String street;

    private String buildingNumber;

    private String apartmentNumber;

}
