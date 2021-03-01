package com.pk.petrolstationmonolith.models.account.password;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestUpdatePassword {

    @NotBlank
    private String oldPassword;

    @NotBlank
    private String newPassword;

}
