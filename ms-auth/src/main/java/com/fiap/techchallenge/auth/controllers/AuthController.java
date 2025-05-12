package com.fiap.techchallenge.auth.controllers;

import com.fiap.techchallenge.auth.dtos.UserDTO;
import com.fiap.techchallenge.auth.dtos.request.AuthRequest;
import com.fiap.techchallenge.auth.dtos.response.AuthResponse;
import com.fiap.techchallenge.auth.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.fiap.techchallenge.auth.utils.JwtUtils.extractToken;

@Slf4j
@RestController
@RequestMapping("/auth")
@Tag(name = "Auth", description = "Operações relacionadas a autenticação")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    @Operation(summary = "Realizar login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição")
    })
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        String token = authService.login(authRequest.getEmail(), authRequest.getPassword());

        return ResponseEntity.ok(new AuthResponse(token));
    }

    @GetMapping("/validate-token")
    @Operation(summary = "Validar token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token válido"),
            @ApiResponse(responseCode = "401", description = "Token inválido")
    })
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String authorizationHeader) {
        String token = extractToken(authorizationHeader);

        UserDTO userDTO = authService.validateToken(token);

        return ResponseEntity.ok(userDTO);
    }
}
