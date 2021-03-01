package com.pk.petrolstationmonolith.models.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestUpdateEmail {

    @NotBlank
    private String token;

    @Email
    private String email;

}
