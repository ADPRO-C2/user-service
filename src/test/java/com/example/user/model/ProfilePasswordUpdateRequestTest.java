package com.example.user.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProfilePasswordUpdateRequestTest {

    @Test
    public void test() {
        String expectedOldPassword = "oldPassword";
        String expectedNewPassword = "newPassword";
        ProfilePasswordUpdateRequest profilePasswordUpdateRequest = new ProfilePasswordUpdateRequest(expectedOldPassword, expectedNewPassword);
        assertEquals(expectedOldPassword, profilePasswordUpdateRequest.getOldPassword());
        assertEquals(expectedNewPassword, profilePasswordUpdateRequest.getNewPassword());
    }
}
