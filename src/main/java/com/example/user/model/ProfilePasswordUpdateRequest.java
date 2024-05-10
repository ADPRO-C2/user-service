package com.example.user.model;

public class ProfilePasswordUpdateRequest {
    private final String oldPassword;
    private final String newPassword;

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
