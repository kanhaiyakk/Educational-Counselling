package com.boot.educationalCounselling.backend.service;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


import java.util.Optional;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.boot.educationalCounselling.backend.dto.LoginDto;
import com.boot.educationalCounselling.backend.dto.RegisterDto;
import com.boot.educationalCounselling.backend.entity.Role;
import com.boot.educationalCounselling.backend.entity.User;
import com.boot.educationalCounselling.backend.repository.RoleRepository;
import com.boot.educationalCounselling.backend.repository.UserRepository;

class AuthServiceImplTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthServiceImpl authService;

    @Captor
    private ArgumentCaptor<User> userCaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void login_WithValidCredentials_ShouldReturnSuccessMessage() {
        // Arrange
        LoginDto loginDto = new LoginDto();
        loginDto.setEmail("test@example.com");
        loginDto.setPassword("password");
        given(userRepository.existsByEmail(loginDto.getEmail())).willReturn(true);
        given(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .willReturn(mockAuthentication());

        // Act
        String result = authService.login(loginDto);

        // Assert
        assertEquals("User Logged-in successfully", result);
        verify(userRepository).existsByEmail(loginDto.getEmail());
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
    }

    @Test
    void login_WithInvalidEmail_ShouldReturnErrorMessage() {
        // Arrange
        LoginDto loginDto = new LoginDto();
        loginDto.setEmail("invalid@example.com");
        loginDto.setPassword("password");
        given(userRepository.existsByEmail(loginDto.getEmail())).willReturn(false);

        // Act
        String result = authService.login(loginDto);

        // Assert
        assertEquals("INVALID EMAIL ID\nNO USER FOUND with the entered email id.\nPlease Sign Up first", result);
        verify(userRepository).existsByEmail(loginDto.getEmail());
    }

    @Test
    void register_WithNewEmail_ShouldReturnSuccessMessage() {
        // Arrange
        RegisterDto registerDto = new RegisterDto();
        registerDto.setFirstName("John");
        registerDto.setLastName("Doe");
        registerDto.setEmail("test@example.com");
        registerDto.setPassword("password");

        given(userRepository.existsByEmail(registerDto.getEmail())).willReturn(false);
        given(roleRepository.findByRoleType("ROLE_USER")).willReturn(Optional.of(new Role("ROLE_USER")));

        // Act
        String result = authService.register(registerDto);

        // Assert
        assertEquals("User registered successfully", result);
        verify(userRepository).existsByEmail(registerDto.getEmail());
        verify(roleRepository).findByRoleType("ROLE_USER");
        verify(userRepository).save(userCaptor.capture());
        User capturedUser = userCaptor.getValue();
        assertEquals(registerDto.getFirstName(), capturedUser.getFirstName());
        assertEquals(registerDto.getLastName(), capturedUser.getLastName());
        assertEquals(registerDto.getEmail(), capturedUser.getEmail());
        assertEquals(registerDto.getPassword(), capturedUser.getPassword());
        assertEquals(1, capturedUser.getRoles().size());
    }

    @Test
    void register_WithExistingEmail_ShouldReturnErrorMessage() {
        // Arrange
        RegisterDto registerDto = new RegisterDto();
        registerDto.setFirstName("John");
        registerDto.setLastName("Doe");
        registerDto.setEmail("existing@example.com");
        registerDto.setPassword("password");

        given(userRepository.existsByEmail(registerDto.getEmail())).willReturn(true);

        // Act
        String result = authService.register(registerDto);

        // Assert
        assertEquals("Email id already exists", result);
        verify(userRepository).existsByEmail(registerDto.getEmail());
    }

    private Authentication mockAuthentication() {
        return new UsernamePasswordAuthenticationToken("test@example.com", "password");
    }
}

