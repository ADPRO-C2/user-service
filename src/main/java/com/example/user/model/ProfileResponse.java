package com.example.user.model;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ProfileResponse {
    private final String message;
    private final Integer id;
    private final String username;
    private final String email;

    public ProfileResponse(String message, Integer id, String username, String email) {
        this.message = message;
        this.id = id;
        this.username = username;
        this.email = email;
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
}
