package com.pk.petrolstationmonolith.models.account.password;

public class RequestNewPassword {

    private String newPassword;

    public RequestNewPassword() {
    }

    public RequestNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
