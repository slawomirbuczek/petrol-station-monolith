package com.pk.petrolstationmonolith.models.account.registration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IndividualRegistrationCredentials {

    @Email(message = "Email should be valid")
    private String email;


    @NotBlank(message = "First name cannot be blank")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    private String lastName;

    @Size(min = 11, max = 11, message = "Pesel should be valid")
    private String pesel;

    @Size(min = 10, max = 10, message = "Nip should be valid")
    private String nip;


    @NotBlank(message = "Country name cannot be blank")
    private String country;

    @NotBlank(message = "City name cannot be blank")
    private String city;

    @NotBlank(message = "Zip cannot be blank")
    private String zip;

    @NotBlank(message = "Street name cannot be blank")
    private String street;

    @NotBlank(message = "Building number cannot be blank")
    private String buildingNumber;

    private String apartmentNumber;

}
