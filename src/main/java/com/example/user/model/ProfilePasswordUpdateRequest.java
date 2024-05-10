package com.example.user.model;

public class ProfilePasswordUpdateRequest {
    private String oldPassword;
    private String newPassword;

    public ProfilePasswordUpdateRequest() {
    }

    public ProfilePasswordUpdateRequest(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }
}
