package com.example.user.service;

import com.example.user.model.ProfileResponse;
import com.example.user.model.Token;
import com.example.user.model.User;
import com.example.user.repository.TokenRepository;
import com.example.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProfileServiceTest {

    @InjectMocks
    private ProfileService profileService;

    @Mock
    private TokenRepository tokenRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;


    @Test
    public void testGetProfile() {
        String token = "testToken";
        User user = new User();
        Token tokenEntity = new Token();
        tokenEntity.setUser(user);
        when(tokenRepository.findByToken(token)).thenReturn(Optional.of(tokenEntity));

        ResponseEntity<ProfileResponse> response = profileService.getProfile(token);

        assertEquals("User profile retrieved successfully", response.getBody().getMessage());
    }

    @Test
    public void testGetProfileInvalidToken() {
        String token = "testToken";
        when(tokenRepository.findByToken(token)).thenReturn(Optional.empty());

        ResponseEntity<ProfileResponse> response = profileService.getProfile(token);

        assertEquals("Invalid token", response.getBody().getMessage());
    }

    @Test
    public void testUpdatePassword() {
        String token = "testToken";
        String oldPassword = "oldPassword";
        String newPassword = "newPassword";
        User user = new User();
        user.setPassword("encodedOldPassword");
        Token tokenEntity = new Token();
        tokenEntity.setUser(user);
        when(tokenRepository.findByToken(token)).thenReturn(Optional.of(tokenEntity));
        when(passwordEncoder.matches(oldPassword, user.getPassword())).thenReturn(true);

        ResponseEntity<ProfileResponse> response = profileService.updatePassword(token, oldPassword, newPassword);

        assertEquals("Password updated successfully", response.getBody().getMessage());
    }

    @Test
    public void testUpdatePasswordInvalidToken() {
        String token = "testToken";
        String oldPassword = "oldPassword";
        String newPassword = "newPassword";
        when(tokenRepository.findByToken(token)).thenReturn(Optional.empty());

        ResponseEntity<ProfileResponse> response = profileService.updatePassword(token, oldPassword, newPassword);

        assertEquals("Invalid token", response.getBody().getMessage());
    }

    @Test
    public void testUpdatePasswordIncorrectOldPassword() {
        String token = "testToken";
        String newPassword = "newPassword";
        User user = new User();
        user.setPassword("encodedOldPassword");
        Token tokenEntity = new Token();
        tokenEntity.setUser(user);
        when(tokenRepository.findByToken(token)).thenReturn(Optional.of(tokenEntity));
        when(passwordEncoder.matches("wrongOldPassword", user.getPassword())).thenReturn(false);

        ResponseEntity<ProfileResponse> response = profileService.updatePassword(token, "wrongOldPassword", newPassword);

        assertEquals("Old password is incorrect", response.getBody().getMessage());
    }

    @Test
    public void testUpdateAddress() {
        String token = "testToken";
        String address = "newAddress";
        User user = new User();
        Token tokenEntity = new Token();
        tokenEntity.setUser(user);
        when(tokenRepository.findByToken(token)).thenReturn(Optional.of(tokenEntity));

        ResponseEntity<ProfileResponse> response = profileService.updateAddress(token, address);

        assertEquals("Address updated successfully", response.getBody().getMessage());
    }

    @Test
    public void testUpdateAddressInvalidToken() {
        String token = "testToken";
        String address = "newAddress";
        when(tokenRepository.findByToken(token)).thenReturn(Optional.empty());

        ResponseEntity<ProfileResponse> response = profileService.updateAddress(token, address);

        assertEquals("Invalid token", response.getBody().getMessage());
    }

    @Test
    public void testUpdateBalance() {
        String token = "testToken";
        long balance = 100;
        User user = new User();
        Token tokenEntity = new Token();
        tokenEntity.setUser(user);
        when(tokenRepository.findByToken(token)).thenReturn(Optional.of(tokenEntity));
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        ResponseEntity<ProfileResponse> response = profileService.updateBalance(token, 1, balance);

        assertEquals("Balance updated successfully", response.getBody().getMessage());
    }

    @Test
    public void testUpdateBalanceInvalidToken() {
        String token = "testToken";
        long balance = 100;
        when(tokenRepository.findByToken(token)).thenReturn(Optional.empty());

        ResponseEntity<ProfileResponse> response = profileService.updateBalance(token, 1, balance);

        assertEquals("Invalid token", response.getBody().getMessage());
    }

    @Test
    public void testUpdateBalanceUserNotFound() {
        String token = "testToken";
        long balance = 100;
        User user = new User();
        Token tokenEntity = new Token();
        tokenEntity.setUser(user);
        when(tokenRepository.findByToken(token)).thenReturn(Optional.of(tokenEntity));
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        ResponseEntity<ProfileResponse> response = profileService.updateBalance(token, 1, balance);

        assertEquals("User not found", response.getBody().getMessage());
    }

    @Test
    public void testDeleteProfile() {
        String token = "testToken";
        User user = new User();
        Token tokenEntity = new Token();
        tokenEntity.setUser(user);
        when(tokenRepository.findByToken(token)).thenReturn(Optional.of(tokenEntity));

        ResponseEntity<ProfileResponse> response = profileService.deleteProfile(token);

        assertEquals("User profile deleted successfully", response.getBody().getMessage());
    }

    @Test
    public void testDeleteProfileInvalidToken() {
        String token = "testToken";
        when(tokenRepository.findByToken(token)).thenReturn(Optional.empty());

        ResponseEntity<ProfileResponse> response = profileService.deleteProfile(token);

        assertEquals("Invalid token", response.getBody().getMessage());
    }
}