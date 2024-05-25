package com.example.user.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProfileResponseTest {

    @Test
    public void testProfileResponse() {
        String expectedMessage = "testMessage";
        Integer expectedId = 1;
        String expectedUsername = "testUsername";
        String expectedEmail = "testEmail@example.com";
        String expectedAddress = "testAddress";
        Long expectedBalance = 0L;

        ProfileResponse profileResponse = new ProfileResponse(expectedMessage, expectedId, expectedUsername, expectedEmail, expectedAddress, expectedBalance);

        assertEquals(expectedMessage, profileResponse.getMessage());
        assertEquals(expectedId, profileResponse.getId());
        assertEquals(expectedUsername, profileResponse.getUsername());
        assertEquals(expectedEmail, profileResponse.getEmail());
        assertEquals(expectedAddress, profileResponse.getAddress());
        assertEquals(expectedBalance, profileResponse.getBalance());
    }
}