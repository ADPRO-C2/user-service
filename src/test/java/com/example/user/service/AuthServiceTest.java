package com.example.user.service;

import com.example.user.model.AuthResponse;
import com.example.user.model.Token;
import com.example.user.model.User;
import com.example.user.repository.TokenRepository;
import com.example.user.repository.UserRepository;
import com.example.user.service.JWTService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @InjectMocks
    private AuthService authService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JWTService jwtService;

    @Mock
    private TokenRepository tokenRepository;

    @Mock
    private AuthenticationManager authenticationManager;

    private User user;
    private User user2;
    private Token token;

    @BeforeEach
    public void setup() {
        user = new User();
        user.setUsername("testUser");
        user.setPassword("testPassword");
        user2 = new User();
        user2.setUsername("testUser2");
        user2.setPassword("testPassword2");
        token = new Token();
        token.setToken("testToken");
    }

    @Test
    public void testRegister() {
        when(userRepository.findByUsername(user.getUsername())).thenReturn(java.util.Optional.empty());
        when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(jwtService.saveUserToken(user)).thenReturn(token);

        ResponseEntity<AuthResponse> response = authService.register(user);

        assertEquals(token.getToken(), response.getBody().getToken());
        assertEquals("User registered successfully", response.getBody().getMessage());
    }

    @Test
    public void testRegisterUsernameTaken() {
        when(userRepository.findByUsername(user.getUsername())).thenReturn(java.util.Optional.of(user));

        ResponseEntity<AuthResponse> response = authService.register(user);

        assertNull(response.getBody().getToken());
        assertEquals("Username is already taken", response.getBody().getMessage());
    }

    @Test
    public void testRegisterPasswordTooShort() {
        user.setPassword("short");
        when(userRepository.findByUsername(user.getUsername())).thenReturn(java.util.Optional.empty());

        ResponseEntity<AuthResponse> response = authService.register(user);

        assertNull(response.getBody().getToken());
        assertEquals("Password must be at least 8 characters", response.getBody().getMessage());
    }

    @Test
    public void testAuthenticate() {
        when(userRepository.findByUsername(user.getUsername())).thenReturn(java.util.Optional.of(user));
        when(jwtService.saveUserToken(user)).thenReturn(token);

        ResponseEntity<AuthResponse> response = authService.authenticate(user);

        assertEquals(token.getToken(), response.getBody().getToken());
        assertEquals("User authenticated successfully", response.getBody().getMessage());
    }

    @Test
    public void testAuthenticateNotFound() {
        when(userRepository.findByUsername(user.getUsername())).thenReturn(java.util.Optional.empty());

        ResponseEntity<AuthResponse> response = authService.authenticate(user);

        assertNull(response.getBody().getToken());
        assertEquals("User not found", response.getBody().getMessage());
    }
}