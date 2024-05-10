package com.example.user.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProfilePasswordUpdateRequestTest {

    @Test
    public void test() {
        String expectedOldPassword = "oldPassword";
        String expectedNewPassword = "newPassword";
        ProfilePasswordUpdateRequest profilePasswordUpdateRequest = new ProfilePasswordUpdateRequest(expectedOldPassword, expectedNewPassword);
        ProfilePasswordUpdateRequest profilePasswordUpdateRequestTest = new ProfilePasswordUpdateRequest();
        assertEquals(expectedOldPassword, profilePasswordUpdateRequest.getOldPassword());
        assertEquals(expectedNewPassword, profilePasswordUpdateRequest.getNewPassword());
        assertNotNull(profilePasswordUpdateRequestTest);
    }
}
