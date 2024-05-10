package com.example.user.model;

public class ProfileResponse {
    private final String message;
    private final Integer id;
    private final String username;
    private final String email;
    private final String address;

    public ProfileResponse(String message, Integer id, String username, String email, String address) {
        this.message = message;
        this.id = id;
        this.username = username;
        this.email = email;
        this.address = address;
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
}
