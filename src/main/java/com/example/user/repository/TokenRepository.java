package com.example.user.repository;

import com.example.user.model.Token;
import com.example.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {
    Optional<Token> findByUser(User user);
    Optional<Token> findByJwtToken(String token);
}