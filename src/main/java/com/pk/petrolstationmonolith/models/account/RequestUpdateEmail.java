package com.pk.petrolstationmonolith.models.account;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class RequestUpdateEmail {

    @NotBlank
    private String token;

    @Email
    private String email;

    public RequestUpdateEmail() {
    }

    public RequestUpdateEmail(@NotBlank String token, @Email String email) {
        this.token = token;
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
