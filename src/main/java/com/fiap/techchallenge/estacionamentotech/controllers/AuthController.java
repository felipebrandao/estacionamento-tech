package com.fiap.techchallenge.estacionamentotech.controllers;

import com.fiap.techchallenge.estacionamentotech.dtos.request.AuthRequest;
import com.fiap.techchallenge.estacionamentotech.dtos.response.AuthResponse;
import com.fiap.techchallenge.estacionamentotech.security.JwtTokenProvider;
import com.fiap.techchallenge.estacionamentotech.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthService authService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, AuthService authService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        String token = authService.login(authRequest.getUsername(), authRequest.getPassword());

        return ResponseEntity.ok(new AuthResponse(token));
    }
}
