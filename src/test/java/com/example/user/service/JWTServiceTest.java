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

    private Token staticToken;
    private User staticUser;

    @BeforeEach
    public void setup() {
        ReflectionTestUtils.setField(jwtService, "key", "ca01c1636a92b9ebd100630a0d4565258c2d70e3057938b09ca3ebc991be40ae");
        staticUser = new User();
        staticUser.setUsername("testUser");
        staticToken = jwtService.saveUserToken(staticUser);
    }

    @Test
    public void testExtractUsername() {
        String actualUsername = jwtService.extractUsername(staticToken.getJwtToken());
        assertEquals(staticUser.getUsername(), actualUsername);
    }

    @Test
    public void testIsValid() {
        when(tokenRepository.findByJwtToken(staticToken.getJwtToken())).thenReturn(Optional.of(staticToken));
        boolean isValid = jwtService.isValid(staticToken.getJwtToken(), staticUser);
        assertTrue(isValid);
    }

    @Test
    public void testIsValidTokenNotFound() {
        when(tokenRepository.findByJwtToken(staticToken.getJwtToken())).thenReturn(Optional.empty());
        boolean isValid = jwtService.isValid(staticToken.getJwtToken(), staticUser);
        assertFalse(isValid);
    }

    @Test
    public void testIsNotValid() {
        when(tokenRepository.findByJwtToken(staticToken.getJwtToken())).thenReturn(Optional.of(staticToken));
        User user = new User();
        user.setUsername("testUser2");
        boolean isValid = jwtService.isValid(staticToken.getJwtToken(), user);
        assertFalse(isValid);
    }

    @Test
    public void testIsNotValidLoggedOut() {
        staticToken.setLoggedOut(true);
        when(tokenRepository.findByJwtToken(staticToken.getJwtToken())).thenReturn(Optional.of(staticToken));
        boolean isValid = jwtService.isValid(staticToken.getJwtToken(), staticUser);
        assertFalse(isValid);
    }


    @Test
    public void testSaveUserToken() {
        Token token = jwtService.saveUserToken(staticUser);
        verify(tokenRepository, times(1)).save(token);
    }

    @Test
    public void testRevokeTokenByUser() {
        when(tokenRepository.findByUser(staticUser)).thenReturn(Optional.of(staticToken));
        Token revokedToken = jwtService.revokeTokenByUser(staticUser);
        verify(tokenRepository, times(1)).delete(staticToken);
        assertEquals(staticToken, revokedToken);
    }

    @Test
    public void testRevokeTokenByUserReturnsNull() {
        when(tokenRepository.findByUser(staticUser)).thenReturn(Optional.empty());
        Token revokedToken = jwtService.revokeTokenByUser(staticUser);
        assertNull(revokedToken);
    }
}