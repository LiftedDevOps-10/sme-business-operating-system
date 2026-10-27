package com.lifted.smebos.controller;

import com.lifted.smebos.dto.auth.LoginRequest;
import com.lifted.smebos.dto.auth.LoginResponse;
import com.lifted.smebos.security.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest request
    ) {
        return ResponseEntity.ok(
                authService.login(request)
        );
    }
}
