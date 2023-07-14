package com.boot.educationalCounselling.backend.controllertest;


import com.boot.educationalCounselling.backend.controller.AuthController;
import com.boot.educationalCounselling.backend.dto.LoginDto;
import com.boot.educationalCounselling.backend.dto.RegisterDto;
import com.boot.educationalCounselling.backend.service.AuthService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;

import jakarta.validation.ValidationException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthControllerTest {

    private AuthController authController;

    @Mock
    private AuthService authService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        authController = new AuthController(authService);
    }

    @Test
    public void testLogin_Success() {
        // Arrange
        LoginDto loginDto = new LoginDto("username", "password");
        String response = "Success";
        when(authService.login(loginDto)).thenReturn(response);

        // Act
        ResponseEntity<String> result = authController.login(loginDto);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
        verify(authService, times(1)).login(loginDto);
    }

    @Test
    public void testRegister_Success() {
        // Arrange
        RegisterDto registerDto = new RegisterDto("username", "password", "email@example.com", null);
        String response = "Success";
        when(authService.register(registerDto)).thenReturn(response);

        // Act
        ResponseEntity<String> result = authController.register(registerDto);

        // Assert
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(response, result.getBody());
        verify(authService, times(1)).register(registerDto);
    }

    @Test
    public void testLogin_InvalidInput() {
        // Arrange
        LoginDto loginDto = new LoginDto("", "");
        when(authService.login(loginDto)).thenThrow(new ValidationException("Invalid input"));

        // Act
        ResponseEntity<String> result = authController.login(loginDto);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        verify(authService, times(1)).login(loginDto);
    }

    @Test
    public void testRegister_InvalidInput() {
        // Arrange
        RegisterDto registerDto = new RegisterDto("", "", "", null);
        when(authService.register(registerDto)).thenThrow(new ValidationException("Invalid input"));

        // Act
        ResponseEntity<String> result = authController.register(registerDto);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        verify(authService, times(1)).register(registerDto);
    }
}
