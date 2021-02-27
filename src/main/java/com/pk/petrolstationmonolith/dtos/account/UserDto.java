package com.pk.petrolstationmonolith.dtos.account;

import com.pk.petrolstationmonolith.enums.account.Roles;
import com.pk.petrolstationmonolith.enums.account.UserType;

public class UserDto {

    private long id;

    private String email;

    private Roles role;

    private UserType userType;

    public UserDto() {
    }

    public UserDto(long id, String email) {
        this.id = id;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role.name();
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public String getUserType() {
        return userType.name();
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

}
