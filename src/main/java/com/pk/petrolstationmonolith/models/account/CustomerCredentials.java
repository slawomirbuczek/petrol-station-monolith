package com.pk.petrolstationmonolith.models.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerCredentials {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
