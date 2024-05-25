package com.example.user.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProfileBalanceUpdateRequestTest {

    @Test
    public void test() {
        long expectedBalance = 1000L;
        ProfileBalanceUpdateRequest profileBalanceUpdateRequest = new ProfileBalanceUpdateRequest(expectedBalance);
        ProfileBalanceUpdateRequest profileBalanceUpdateRequestTest = new ProfileBalanceUpdateRequest();
        assertEquals(expectedBalance, profileBalanceUpdateRequest.getNewBalance());
        assertNotNull(profileBalanceUpdateRequestTest);
    }

}
