package com.example.employeeapi.infrastructure.adapter.in.web;

import com.example.employeeapi.infrastructure.adapter.in.dto.AuthResponse;
import com.example.employeeapi.infrastructure.adapter.in.dto.LoginRequest;
import com.example.employeeapi.infrastructure.security.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;

    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        String token = jwtService.generateToken(request.username());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}