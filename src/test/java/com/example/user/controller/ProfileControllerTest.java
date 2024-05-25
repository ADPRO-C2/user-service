package com.example.user.controller;

import com.example.user.model.ProfileAddressUpdateRequest;
import com.example.user.model.ProfileBalanceUpdateRequest;
import com.example.user.model.ProfilePasswordUpdateRequest;
import com.example.user.model.ProfileResponse;
import com.example.user.service.ProfileService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProfileControllerTest {

    @InjectMocks
    private ProfileController profileController;

    @Mock
    private ProfileService profileService;


    @Test
    public void testGetProfile() {
        String token = "Bearer testToken";
        ProfileResponse expectedResponse = new ProfileResponse("User profile retrieved successfully", 1, "username", "email", "address", 0L);
        when(profileService.getProfile(token.substring(7))).thenReturn(ResponseEntity.ok(expectedResponse));
        ResponseEntity<ProfileResponse> response = profileController.getProfile(token);
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    public void testUpdatePassword() {
        String token = "Bearer testToken";
        String oldPassword = "oldPassword";
        String newPassword = "newPassword";
        ProfilePasswordUpdateRequest request = new ProfilePasswordUpdateRequest(oldPassword, newPassword);
        ProfileResponse expectedResponse = new ProfileResponse("Password updated successfully", null, null, null, null, null);
        when(profileService.updatePassword(token.substring(7), oldPassword, newPassword)).thenReturn(ResponseEntity.ok(expectedResponse));
        ResponseEntity<ProfileResponse> response = profileController.updatePassword(token, request);
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    public void testUpdateAddress() {
        String token = "Bearer testToken";
        String address = "newAddress";
        ProfileAddressUpdateRequest request = new ProfileAddressUpdateRequest(address);
        ProfileResponse expectedResponse = new ProfileResponse("Address updated successfully", null, null, null, null, null);
        when(profileService.updateAddress(token.substring(7), address)).thenReturn(ResponseEntity.ok(expectedResponse));
        ResponseEntity<ProfileResponse> response = profileController.updateAddress(token, request);
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    public void testUpdateBalance() {
        String token = "Bearer testToken";
        long balance = 1000L;
        int userId = 1;
        ProfileBalanceUpdateRequest request = new ProfileBalanceUpdateRequest(1, balance);
        ProfileResponse expectedResponse = new ProfileResponse("Balance updated successfully", null, null, null, null, null);
        when(profileService.updateBalance(token.substring(7), userId, balance)).thenReturn(ResponseEntity.ok(expectedResponse));
        ResponseEntity<ProfileResponse> response = profileController.updateBalance(token, request);
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    public void testDeleteProfile() {
        String token = "Bearer testToken";
        ProfileResponse expectedResponse = new ProfileResponse("Profile deleted successfully", null, null, null, null, null);
        when(profileService.deleteProfile(token.substring(7))).thenReturn(ResponseEntity.ok(expectedResponse));
        ResponseEntity<ProfileResponse> response = profileController.deleteProfile(token);
        assertEquals(expectedResponse, response.getBody());
    }
}