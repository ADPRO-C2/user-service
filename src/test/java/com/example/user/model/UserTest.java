package com.example.user.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UserTest {
    User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1);
        user.setUsername("testUser");
        user.setEmail("testUser@example.com");
        user.setAddress("testAdress");
        user.setPassword("password");
        user.setRole(Role.USER);
        user.setBalance(0);
    }

    @Test
    void getId() {
        assertEquals(1, user.getId());
    }

    @Test
    void getUsername() {
        assertEquals("testUser", user.getUsername());
    }

    @Test
    void getEmail() {
        assertEquals("testUser@example.com", user.getEmail());
    }

    @Test
    void getAdress() {
        assertEquals("testAdress", user.getAddress());
    }

    @Test
    void getPassword() {
        assertEquals("password", user.getPassword());
    }

    @Test
    void getRole() {
        assertEquals(Role.USER, user.getRole());
    }

    @Test
    void isAccountNonExpired() {
        assertTrue(user.isAccountNonExpired());
    }

    @Test
    void isAccountNonLocked() {
        assertTrue(user.isAccountNonLocked());
    }

    @Test
    void isCredentialsNonExpired() {
        assertTrue(user.isCredentialsNonExpired());
    }

    @Test
    void isEnabled() {
        assertTrue(user.isEnabled());
    }

    @Test
    void getBalance() {
        assertEquals(0, user.getBalance());
    }

    @Test
    void getAuthorities() {
        assertEquals("USER", user.getAuthorities().iterator().next().getAuthority());
    }

    @Test
    void setId() {
        user.setId(2);
        assertEquals(2, user.getId());
    }

    @Test
    void setUsername() {
        user.setUsername("newUser");
        assertEquals("newUser", user.getUsername());
    }

    @Test
    void setEmail() {
        user.setEmail("newUser@example.com");
        assertEquals("newUser@example.com", user.getEmail());
    }

    @Test
    void setAdress() {
        user.setAddress("newAdress");
        assertEquals("newAdress", user.getAddress());
    }

    @Test
    void setPassword() {
        user.setPassword("newPassword");
        assertEquals("newPassword", user.getPassword());
    }

    @Test
    void setRole() {
        user.setRole(Role.STAFF);
        assertEquals(Role.STAFF, user.getRole());
    }

    @Test
    void setBalance() {
        user.setBalance(100);
        assertEquals(100, user.getBalance());
    }
}