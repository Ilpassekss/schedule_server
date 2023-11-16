package com.example.demo.Regsitration.Controllers;


import com.example.demo.Regsitration.Controllers.Responses.AuthResponse;
import com.example.demo.Regsitration.Controllers.Responses.Response;
import com.example.demo.Regsitration.Requests.AuthRequest;
import com.example.demo.Regsitration.Requests.RefreshJWTRequest;
import com.example.demo.Regsitration.Requests.RegisterRequest;
import com.example.demo.Regsitration.Services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authenticationService;

    //user login endpoint /api/v1/auth/login
    //send user email and password
    //after login return access and refresh tokens
    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody AuthRequest authRequest) {

        System.out.println(authRequest.toString());

        return ResponseEntity.ok(authenticationService.userAuthentication(authRequest));
    }

    // user registration endpoint /api/v1/auth/register here you have to send
    // user first and second name, email, password, and country
    // after registration return 2 tokens (access and refresh)
    @PostMapping("/register")
    public ResponseEntity<Response> register(@RequestBody RegisterRequest registerRequest) {
        System.out.println(registerRequest.toString());

        AuthResponse authResponse;


        return ResponseEntity.ok(authenticationService.userRegistration(registerRequest));
    }

    // in this endpoint : /api/auth/refresh
    // this endpoint return new access token and non expired refresh token
    @PostMapping("/refresh")
    public ResponseEntity<Response> getNewAccessToken(@RequestBody RefreshJWTRequest request) {
        return ResponseEntity.ok(authenticationService.refreshAccessToken(request.getRefreshToken()));
    }

}
