package com.example.transportationManagement.service;


import com.example.transportationManagement.entity.RefreshToken;
import com.example.transportationManagement.entity.User;
import com.example.transportationManagement.repository.RefreshTokenRepository;
import com.example.transportationManagement.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
public class RefreshTokenService {
    private int refreshTokenDurationMs = 60000*60*24*2;

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    public RefreshTokenService(RefreshTokenRepository repo, UserRepository userRepo) {
        this.refreshTokenRepository = repo;
        this.userRepository = userRepo;
    }

    public RefreshToken createRefreshToken(Long userId) {
        User user=userRepository.findById(userId).get();
        return refreshTokenRepository.findByUser(user)
                .map(existingToken -> {
                    // UPDATE existing token
                    existingToken.setToken(UUID.randomUUID().toString());
                    existingToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
                    return refreshTokenRepository.save(existingToken);
                })
                .orElseGet(() -> {
                    // CREATE new token
                    RefreshToken newToken = RefreshToken.builder()
                            .user(user)
                            .token(UUID.randomUUID().toString())
                            .expiryDate(Instant.now().plusMillis(refreshTokenDurationMs))
                            .build();
                    return refreshTokenRepository.save(newToken);
                });
    }

    public boolean isTokenExpired(RefreshToken token) {
        return token.getExpiryDate().isBefore(Instant.now());
    }
}