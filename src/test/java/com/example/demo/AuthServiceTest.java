package com.example.demo;

import com.example.demo.Regsitration.Controllers.Responses.AuthResponse;
import com.example.demo.Regsitration.Controllers.Responses.Response;
import com.example.demo.Regsitration.Entities.RefreshToken;
import com.example.demo.Regsitration.Entities.User;
import com.example.demo.Regsitration.Repos.RefreshTokenRepo;
import com.example.demo.Regsitration.Repos.UserRepo;
import com.example.demo.Regsitration.Requests.AuthRequest;
import com.example.demo.Regsitration.Requests.RegisterRequest;
import com.example.demo.Regsitration.Services.AuthService;
import com.example.demo.Regsitration.Services.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class AuthServiceTest {

    @InjectMocks
    private AuthService yourService;

    @Mock
    private UserRepo userRepo;

    @Mock
    private RefreshTokenRepo refreshTokenRepo;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUserRegistration() {
        // Arrange

        RegisterRequest registerRequest = RegisterRequest.builder()
                .firstName("Illia")
                .secondName("Pas")
                .email("il@gmail.com")
                .password("1234")
                .country("ukr")
                .build();// create your register request

        User user = User.builder()
                .build(); // create your user

        when(userRepo.countByEmail(registerRequest.getEmail())).thenReturn(0L);
        when(userRepo.save(any(User.class))).thenReturn(user);
        when(userRepo.findByEmail(registerRequest.getEmail())).thenReturn(java.util.Optional.of(user));
        when(jwtService.generateAccessToken(user)).thenReturn("fakeAccessToken");
        when(jwtService.generateRefreshToken(user)).thenReturn("fakeRefreshToken");

        // Act
        Response response = yourService.userRegistration(registerRequest);

        // Assert
        // Add assertions based on your expected behavior
        // For example:
        assertEquals(AuthResponse.class, response.getClass());
    }


    @Test
    void testUserAuthentication() {
        // Arrange
        AuthRequest authRequest = new AuthRequest();
        User user = new User();
        RefreshToken refreshToken = new RefreshToken();

        when(userRepo.findByEmail(authRequest.getEmail())).thenReturn(java.util.Optional.of(user));
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(Mockito.mock(Authentication.class));
        when(refreshTokenRepo.findRefreshTokenByUserEmail(user.getEmail())).thenReturn(java.util.Optional.of(refreshToken));
        when(jwtService.generateAccessToken(user)).thenReturn("fakeAccessToken");
        when(jwtService.generateRefreshToken(user)).thenReturn("fakeRefreshToken");

        // Act
        AuthResponse authResponse = yourService.userAuthentication(authRequest);

        // Assert
        // Add assertions based on your expected behavior
        // For example:
        assertEquals("fakeAccessToken", authResponse.getAccessToken());
        assertEquals("fakeRefreshToken", authResponse.getRefreshToken());
    }
}
