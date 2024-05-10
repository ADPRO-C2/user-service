package com.example.user.controller;

import com.example.user.model.ProfileAddressUpdateRequest;
import com.example.user.model.ProfilePasswordUpdateRequest;
import com.example.user.model.ProfileResponse;
import com.example.user.service.ProfileService;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProfileControllerTest {

    @InjectMocks
    private ProfileController profileController;

    @Mock
    private ProfileService profileService;


    @Test
    public void testGetProfile() {
        String token = "testToken";
        ProfileResponse expectedResponse = new ProfileResponse("User profile retrieved successfully", 1, "username", "email", "address");
        when(profileService.getProfile(token)).thenReturn(ResponseEntity.ok(expectedResponse));
        ResponseEntity<ProfileResponse> response = profileController.getProfile(token);
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    public void testUpdatePassword() {
        String token = "testToken";
        String oldPassword = "oldPassword";
        String newPassword = "newPassword";
        ProfilePasswordUpdateRequest request = new ProfilePasswordUpdateRequest(oldPassword, newPassword);
        ProfileResponse expectedResponse = new ProfileResponse("Password updated successfully", null, null, null, null);
        when(profileService.updatePassword(token, oldPassword, newPassword)).thenReturn(ResponseEntity.ok(expectedResponse));
        ResponseEntity<ProfileResponse> response = profileController.updatePassword(token, request);
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    public void testUpdateAddress() {
        String token = "testToken";
        String address = "newAddress";
        ProfileAddressUpdateRequest request = new ProfileAddressUpdateRequest(address);
        ProfileResponse expectedResponse = new ProfileResponse("Address updated successfully", null, null, null, null);
        when(profileService.updateAddress(token, address)).thenReturn(ResponseEntity.ok(expectedResponse));
        ResponseEntity<ProfileResponse> response = profileController.updateAddress(token, request);
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    public void testDeleteProfile() {
        String token = "testToken";
        HttpServletResponse servletResponse = mock(HttpServletResponse.class);
        ProfileResponse expectedResponse = new ProfileResponse("Profile deleted successfully", null, null, null, null);
        when(profileService.deleteProfile(token)).thenReturn(ResponseEntity.ok(expectedResponse));
        ResponseEntity<ProfileResponse> response = profileController.deleteProfile(token, servletResponse);
        assertEquals(expectedResponse, response.getBody());
    }
}