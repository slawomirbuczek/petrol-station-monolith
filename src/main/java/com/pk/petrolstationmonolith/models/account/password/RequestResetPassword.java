package com.pk.petrolstationmonolith.models.account.password;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestResetPassword {

    @Email
    private String email;

}
