package com.example.user.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TokenTest {

    @Test
    void testToken() {
        Token token = new Token();
        User user = new User();
        user.setUsername("testUser");

        token.setId(1);
        token.setJwtToken("testToken");
        token.setLoggedOut(false);
        token.setUser(user);

        assertEquals(1, token.getId());
        assertEquals("testToken", token.getJwtToken());
        assertFalse(token.isLoggedOut());
        assertEquals("testUser", token.getUser().getUsername());

        // Test setters
        token.setId(2);
        token.setJwtToken("newTestToken");
        token.setLoggedOut(true);
        User newUser = new User();
        newUser.setUsername("newTestUser");
        token.setUser(newUser);

        assertEquals(2, token.getId());
        assertEquals("newTestToken", token.getJwtToken());
        assertEquals(true, token.isLoggedOut());
        assertEquals("newTestUser", token.getUser().getUsername());
    }
}