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

    @NotBlank(message = "Token cannot be blank")
    private String token;

    @Email(message = "Email should be valid")
    private String email;

}
