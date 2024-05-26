package com.example.user.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProfileBalanceUpdateRequestTest {

    @Test
    public void test() {
        long expectedBalance = 1000L;
        ProfileBalanceUpdateRequest profileBalanceUpdateRequest = new ProfileBalanceUpdateRequest(1, expectedBalance);
        ProfileBalanceUpdateRequest profileBalanceUpdateRequestTest = new ProfileBalanceUpdateRequest();

        assertNotNull(profileBalanceUpdateRequest);
        assertNotNull(profileBalanceUpdateRequestTest);
        assertEquals(1, profileBalanceUpdateRequest.getUserId());
        assertEquals(expectedBalance, profileBalanceUpdateRequest.getBalance());
    }

}
