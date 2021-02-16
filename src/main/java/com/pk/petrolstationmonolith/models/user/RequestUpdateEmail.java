package com.pk.petrolstationmonolith.models.user;

import javax.validation.constraints.Email;

public class RequestUpdateEmail {

    @Email
    private String email;

    public RequestUpdateEmail() {
    }

    public RequestUpdateEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
