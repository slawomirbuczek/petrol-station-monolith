package com.pk.petrolstationmonolith.dtos.account;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class AddressDto {

    @NotBlank(message = "Country cannot be blank")
    private String country;

    @NotBlank(message = "City cannot be blank")
    private String city;

    @NotBlank(message = "Zip code cannot be blank")
    private String zip;

    @NotBlank(message = "Street cannot be blank")
    private String street;

    @NotBlank(message = "Building number cannot be blank")
    private String buildingNumber;

    private String apartmentNumber;

}
