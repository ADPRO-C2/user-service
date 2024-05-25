package com.example.user.service;


import com.example.user.model.ProfileResponse;
import com.example.user.model.Token;
import com.example.user.model.User;
import com.example.user.repository.TokenRepository;
import com.example.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public ResponseEntity<ProfileResponse> getProfile(String token) {
        Token storedToken = tokenRepository.findByToken(token).orElse(null);
        if (storedToken == null) {
            return ResponseEntity.badRequest().body(new ProfileResponse("Invalid token", null, null, null, null));
        }
        User user = storedToken.getUser();
        return ResponseEntity.ok(new ProfileResponse("User profile retrieved successfully", user.getId(), user.getUsername(), user.getEmail(), user.getAddress()));
    }

    public ResponseEntity<ProfileResponse> updatePassword(String token, String oldPassword, String newPassword) {
        Token storedToken = tokenRepository.findByToken(token).orElse(null);
        if (storedToken == null) {
            return ResponseEntity.badRequest().body(new ProfileResponse("Invalid token", null, null, null, null));
        }
        User user = storedToken.getUser();
        System.out.println(user.getPassword());
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            return ResponseEntity.badRequest().body(new ProfileResponse("Old password is incorrect", null, null, null, null));
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        return ResponseEntity.ok(new ProfileResponse("Password updated successfully", null, null, null, null));
    }

    public ResponseEntity<ProfileResponse> updateAddress(String token, String address) {
        Token storedToken = tokenRepository.findByToken(token).orElse(null);
        if (storedToken == null) {
            return ResponseEntity.badRequest().body(new ProfileResponse("Invalid token", null, null, null, null));
        }
        User user = storedToken.getUser();
        user.setAddress(address);
        userRepository.save(user);

        return ResponseEntity.ok(new ProfileResponse("Address updated successfully", null, null, null, null));
    }

    public ResponseEntity<ProfileResponse> updateBalance(String token, long balance) {
        Token storedToken = tokenRepository.findByToken(token).orElse(null);
        if (storedToken == null) {
            return ResponseEntity.badRequest().body(new ProfileResponse("Invalid token", null, null, null, null));
        }
        User user = storedToken.getUser();
        user.setBalance(balance);
        userRepository.save(user);

        return ResponseEntity.ok(new ProfileResponse("Balance updated successfully", null, null, null, null));
    }

    public ResponseEntity<ProfileResponse> deleteProfile(String token) {
        Token storedToken = tokenRepository.findByToken(token).orElse(null);
        if (storedToken == null) {
            return ResponseEntity.badRequest().body(new ProfileResponse("Invalid token", null, null, null, null));
        }
        User user = storedToken.getUser();
        tokenRepository.delete(storedToken);
        userRepository.delete(user);

        return ResponseEntity.ok(new ProfileResponse("User profile deleted successfully", null, null, null, null));
    }

}
