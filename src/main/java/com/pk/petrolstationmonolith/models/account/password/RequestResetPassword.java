package com.pk.petrolstationmonolith.models.account.password;

import javax.validation.constraints.Email;

public class RequestResetPassword {

    @Email
    private String email;

    public RequestResetPassword() {
    }

    public RequestResetPassword(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
