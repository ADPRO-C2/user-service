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

        ProfileResponse profileResponse = new ProfileResponse(expectedMessage, expectedId, expectedUsername, expectedEmail);

        assertEquals(expectedMessage, profileResponse.getMessage());
        assertEquals(expectedId, profileResponse.getId());
        assertEquals(expectedUsername, profileResponse.getUsername());
        assertEquals(expectedEmail, profileResponse.getEmail());
    }
}