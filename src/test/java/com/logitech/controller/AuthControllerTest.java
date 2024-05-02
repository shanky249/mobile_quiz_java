package com.logitech.controller;

import com.logitech.controller.AuthController;
import com.logitech.model.AuthRequest;
import com.logitech.model.AuthResponse;
import com.logitech.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;

public class AuthControllerTest {

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLogin_Success() {
        AuthRequest authRequest = new AuthRequest();
        authRequest.setUsername("testUser");
        authRequest.setPassword("testPassword");

        when(authService.authenticate(authRequest)).thenReturn("mockToken");

        ResponseEntity<AuthResponse> responseEntity = authController.login(authRequest);

        assert responseEntity.getStatusCode() == HttpStatus.OK;
        assert responseEntity.getBody().getToken().equals("mockToken");

        verify(authService, times(1)).authenticate(authRequest);
    }

    // Add more test cases for edge cases, invalid inputs, etc.
}
