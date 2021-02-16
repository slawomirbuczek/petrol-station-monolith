package com.pk.petrolstationmonolith.models.password;

public class RequestUpdatePassword {

    private String oldPassword;
    private String newPassword;

    public RequestUpdatePassword() {
    }

    public RequestUpdatePassword(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
