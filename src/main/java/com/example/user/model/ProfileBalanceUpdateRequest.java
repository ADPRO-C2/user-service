package com.example.user.model;

public class ProfileBalanceUpdateRequest {
    private long newBalance;

    public ProfileBalanceUpdateRequest() {
    }

    public ProfileBalanceUpdateRequest(long newBalance) {
        this.newBalance = newBalance;
    }

    public long getNewBalance() {
        return newBalance;
    }
}
