package com.example.user.controller;

import com.example.user.model.AuthResponse;
import com.example.user.model.User;
import com.example.user.service.AuthService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

    @InjectMocks
    private AuthController authController;

    @Mock
    private AuthService authService;


    @Test
    public void testRegister() {
        User user = new User();
        AuthResponse expectedResponse = new AuthResponse("testToken", "User registered successfully");
        when(authService.register(user)).thenReturn(ResponseEntity.ok(expectedResponse));

        ResponseEntity<AuthResponse> response = authController.register(user);

        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    public void testLogin() {
        User user = new User();
        AuthResponse expectedResponse = new AuthResponse("testToken", "User registered successfully");
        when(authService.authenticate(user)).thenReturn(ResponseEntity.ok(expectedResponse));

        ResponseEntity<AuthResponse> response = authController.login(user);

        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    public void testLogout() {
        String token = "Bearer testToken";
        AuthResponse expectedResponse = new AuthResponse(null, "User logged out successfully");
        when(authService.logout("testToken")).thenReturn(ResponseEntity.ok(expectedResponse));

        ResponseEntity<AuthResponse> response = authController.logout(token);

        assertEquals(expectedResponse, response.getBody());
    }
}