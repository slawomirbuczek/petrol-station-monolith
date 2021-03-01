package com.pk.petrolstationmonolith.models.account.password;

public class RequestNewPassword {

    private String newPassword;

    private String email;

    private String token;

    public RequestNewPassword() {
    }

    public RequestNewPassword(String newPassword, String email, String token) {
        this.newPassword = newPassword;
        this.email = email;
        this.token = token;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
