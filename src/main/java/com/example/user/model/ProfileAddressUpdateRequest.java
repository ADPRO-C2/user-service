package com.example.user.model;

public class ProfileAddressUpdateRequest {
    private final String address;

    public ProfileAddressUpdateRequest(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
}
