package com.example.user.service;

import com.example.user.model.AuthResponse;
import com.example.user.model.Role;
import com.example.user.model.Token;
import com.example.user.model.User;
import com.example.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JWTService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    public ResponseEntity<AuthResponse> register(User request) {
        if(repository.findByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body(new AuthResponse(null, "Username is already taken"));
        }
        if (request.getPassword().length() < 8) {
            return ResponseEntity.badRequest().body(new AuthResponse(null, "Password must be at least 8 characters"));
        }
        User user = new User();
        user.setEmail(request.getEmail());
        user.setAddress(request.getAddress());
        user.setUsername(request.getUsername());
        user.setBalance(0L);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);
        repository.save(user);

        return ResponseEntity.ok(new AuthResponse(null, "User registered successfully"));
    }

    public ResponseEntity<AuthResponse> authenticate(User request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        User user = repository.findByUsername(request.getUsername()).orElse(null);
        if (user == null) {
            return ResponseEntity.badRequest().body(new AuthResponse(null, "User not found"));
        }
        jwtService.revokeTokenByUser(user);
        Token jwt = jwtService.saveUserToken(user);

        return ResponseEntity.ok(new AuthResponse(jwt.getJwtToken(), "User authenticated successfully"));
    }

    public ResponseEntity<AuthResponse> logout(String token) {
        String userUsername = jwtService.extractUsername(token);
        User user = repository.findByUsername(userUsername).orElse(null);
        if (user == null) {
            return ResponseEntity.badRequest().body(new AuthResponse(null, "User not found"));
        }
        jwtService.revokeTokenByUser(user);
        return ResponseEntity.ok(new AuthResponse(null, "User logged out successfully"));
    }
}
