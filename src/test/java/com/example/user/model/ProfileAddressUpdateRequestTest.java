package com.example.user.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProfileAddressUpdateRequestTest {

    @Test
    public void test() {
        String expectedStreet = "testStreet";
        ProfileAddressUpdateRequest profileAddressUpdateRequest = new ProfileAddressUpdateRequest(expectedStreet);
        ProfileAddressUpdateRequest profileAddressUpdateRequestTest = new ProfileAddressUpdateRequest();
        assertEquals(expectedStreet, profileAddressUpdateRequest.getNewAddress());
        assertNotNull(profileAddressUpdateRequestTest);
    }
}
