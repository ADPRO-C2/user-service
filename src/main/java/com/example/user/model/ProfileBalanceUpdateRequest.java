package com.example.user.model;

public class ProfileBalanceUpdateRequest {

    private int userId;
    private long balance;

    public ProfileBalanceUpdateRequest() {
    }

    public ProfileBalanceUpdateRequest(int userId, long balance) {
        this.userId = userId;
        this.balance = balance;
    }

    public int getUserId() {
        return userId;
    }

    public long getBalance() {
        return balance;
    }
}
