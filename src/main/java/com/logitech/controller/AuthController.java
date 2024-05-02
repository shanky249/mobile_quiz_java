package com.logitech.controller;

import com.logitech.model.AuthRequest;
import com.logitech.model.AuthResponse;
import com.logitech.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        LOGGER.info("Received login request for user: {}", authRequest.getUsername());
        String token = authService.authenticate(authRequest);
        LOGGER.info("User {} successfully authenticated", authRequest.getUsername());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
