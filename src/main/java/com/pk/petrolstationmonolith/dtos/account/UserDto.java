package com.pk.petrolstationmonolith.dtos.account;

import com.pk.petrolstationmonolith.enums.account.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {

    private long id;

    private String email;

    private Roles role;

}
