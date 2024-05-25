package com.example.user.controller;

import com.example.user.model.ProfileAddressUpdateRequest;
import com.example.user.model.ProfilePasswordUpdateRequest;
import com.example.user.model.ProfileResponse;
import com.example.user.service.ProfileService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/profile")
    public ResponseEntity<ProfileResponse> getProfile(@CookieValue(value = "jwt") String token) {
        return profileService.getProfile(token);
    }

    @PutMapping("/profile/password")
    public ResponseEntity<ProfileResponse> updatePassword(@CookieValue(value = "jwt") String token, @RequestBody ProfilePasswordUpdateRequest request) {
        return profileService.updatePassword(token, request.getOldPassword(), request.getNewPassword());
    }

    @PutMapping("/profile/address")
    public ResponseEntity<ProfileResponse> updateAddress(@CookieValue(value = "jwt") String token, @RequestBody ProfileAddressUpdateRequest request) {
        return profileService.updateAddress(token, request.getNewAddress());
    }

    @PutMapping("/profile/balance")
    public ResponseEntity<ProfileResponse> updateBalance(@CookieValue(value = "jwt") String token, @RequestParam long balance) {
        return profileService.updateBalance(token, balance);
    }

    @DeleteMapping("/profile/delete")
    public ResponseEntity<ProfileResponse> deleteProfile(@CookieValue(value = "jwt") String token, HttpServletResponse response) {
        response.addHeader("Set-Cookie", "jwt=; HttpOnly; SameSite=None; Path=/; Max-Age=0");
        return profileService.deleteProfile(token);
    }
}
