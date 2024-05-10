package com.example.user.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProfileAddressUpdateRequestTest {

    @Test
    public void test() {
        String expectedStreet = "testStreet";
        ProfileAddressUpdateRequest profileAddressUpdateRequest = new ProfileAddressUpdateRequest(expectedStreet);
        assertEquals(expectedStreet, profileAddressUpdateRequest.getAddress());
    }
}
