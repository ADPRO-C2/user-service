package com.example.user.controller;

import com.example.user.model.ProfileAddressUpdateRequest;
import com.example.user.model.ProfileBalanceUpdateRequest;
import com.example.user.model.ProfilePasswordUpdateRequest;
import com.example.user.model.ProfileResponse;
import com.example.user.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/profile")
    public ResponseEntity<ProfileResponse> getProfile(@RequestHeader(value = "Authorization") String token) {
        return profileService.getProfile(token.substring(7));
    }
    @PutMapping("/profile/password")
    public ResponseEntity<ProfileResponse> updatePassword(@RequestHeader(value = "Authorization") String token, @RequestBody ProfilePasswordUpdateRequest request) {
        return profileService.updatePassword(token.substring(7), request.getOldPassword(), request.getNewPassword());
    }

    @PutMapping("/profile/address")
    public ResponseEntity<ProfileResponse> updateAddress(@RequestHeader(value = "Authorization") String token, @RequestBody ProfileAddressUpdateRequest request) {
        return profileService.updateAddress(token.substring(7), request.getNewAddress());
    }

    @PutMapping("/profile/balance")
    public ResponseEntity<ProfileResponse> updateBalance(@RequestHeader(value = "Authorization") String token, @RequestBody ProfileBalanceUpdateRequest request) {
        return profileService.updateBalance(token.substring(7), request.getUserId(), request.getAddedBalance());
    }

    @PutMapping("/profile/myBalance")
    public ResponseEntity<ProfileResponse> decreaseMyBalance(@RequestHeader(value = "Authorization") String token, @RequestBody ProfileBalanceUpdateRequest request) {
        return profileService.decreaseMyBalance(token.substring(7), request.getAddedBalance());
    }

    @DeleteMapping("/profile/delete")
    public ResponseEntity<ProfileResponse> deleteProfile(@RequestHeader(value = "Authorization") String token) {
        return profileService.deleteProfile(token.substring(7));
    }
}
