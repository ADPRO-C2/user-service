package com.example.user.model;

public class ProfileBalanceUpdateRequest {

    private int userId;
    private long addedBalance;

    public ProfileBalanceUpdateRequest() {
    }

    public ProfileBalanceUpdateRequest(int userId, long addedBalance) {
        this.userId = userId;
        this.addedBalance = addedBalance;
    }

    public int getUserId() {
        return userId;
    }

    public long getAddedBalance() {
        return addedBalance;
    }
}
