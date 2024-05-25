package com.example.user.model;

public class ProfileResponse {
    private final String message;
    private final Integer id;
    private final String username;
    private final String email;
    private final String address;
    private final Long balance;

    public ProfileResponse(String message, Integer id, String username, String email, String address, Long balance) {
        this.message = message;
        this.id = id;
        this.username = username;
        this.email = email;
        this.address = address;
        this.balance = balance;
    }
    public String getMessage() {
        return message;
    }
    public Integer getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
    }
    public String getAddress() {
        return address;
    }
    public Long getBalance() {
        return balance;
    }
}
