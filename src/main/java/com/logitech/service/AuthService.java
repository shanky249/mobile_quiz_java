// com/example/service/AuthService.java
package com.logitech.service;

import com.logitech.controller.AuthController;
import com.logitech.model.AuthRequest;
import com.logitech.model.User;
import com.logitech.repository.UserRepository;
import com.logitech.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthService.class);

    private final UserRepository userRepository;
    private final JwtTokenUtil jwtTokenUtil;

    public String authenticate(AuthRequest authRequest) {
        String username = authRequest.getUsername();
        String password = authRequest.getPassword();

        LOGGER.info("Authenticating user: {}", username);

        // Fetch user from repository
        User user = userRepository.findByUsername(username);

        // Check if user exists and password matches
        if (user != null && user.getPassword().equals(password)) {
            LOGGER.info("User {} successfully authenticated", username);
            return jwtTokenUtil.generateToken(username);
        } else {
            LOGGER.error("Authentication failed for user: {}", username);
            throw new IllegalArgumentException("Invalid username or password");
        }
    }
}
