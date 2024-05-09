package com.example.user.service;

import com.example.user.model.Token;
import com.example.user.model.User;
import com.example.user.repository.TokenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class JWTServiceTest {

    @Mock
    private TokenRepository tokenRepository;
    @InjectMocks
    private JWTService jwtService;

    private Token token;
    private User user;

    @BeforeEach
    public void setup() {
        ReflectionTestUtils.setField(jwtService, "SECRET_KEY", "ca01c1636a92b9ebd100630a0d4565258c2d70e3057938b09ca3ebc991be40ae");
        user = new User();
        user.setUsername("testUser");
        token = jwtService.saveUserToken(user);
    }

    @Test
    public void testExtractUsername() {
        String actualUsername = jwtService.extractUsername(token.getToken());
        assertEquals(user.getUsername(), actualUsername);
    }

    @Test
    public void testIsValid() {
        when(tokenRepository.findByToken(token.getToken())).thenReturn(Optional.of(token));
        boolean isValid = jwtService.isValid(token.getToken(), user);
        assertTrue(isValid);
    }

    @Test
    public void testIsValidThrowsException() {
        when(tokenRepository.findByToken(token.getToken())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> jwtService.isValid(token.getToken(), user));
    }

    @Test
    public void testIsNotValid() {
        when(tokenRepository.findByToken(token.getToken())).thenReturn(Optional.of(token));
        User user = new User();
        user.setUsername("testUser2");
        boolean isValid = jwtService.isValid(token.getToken(), user);
        assertFalse(isValid);
    }

    @Test
    public void testIsNotValidLoggedOut() {
        token.setLoggedOut(true);
        when(tokenRepository.findByToken(token.getToken())).thenReturn(Optional.of(token));
        boolean isValid = jwtService.isValid(token.getToken(), user);
        assertFalse(isValid);
    }


    @Test
    public void testSaveUserToken() {
        Token token = jwtService.saveUserToken(user);
        verify(tokenRepository, times(1)).save(token);
    }

    @Test
    public void testRevokeTokenByUser() {
        when(tokenRepository.findByUser(user)).thenReturn(Optional.of(token));
        Token revokedToken = jwtService.revokeTokenByUser(user);
        assertEquals(token.getToken(), revokedToken.getToken());
        assertTrue(revokedToken.isLoggedOut());
    }

    @Test
    public void testRevokeTokenByUserThrowsException() {
        User user = new User();
        user.setUsername("testUsername");

        when(tokenRepository.findByUser(user)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> jwtService.revokeTokenByUser(user));
    }
}