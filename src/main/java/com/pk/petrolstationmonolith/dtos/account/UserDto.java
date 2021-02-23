package com.pk.petrolstationmonolith.dtos.account;

public class UserDto {

    private long id;

    private String email;

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
}
