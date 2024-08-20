package com.fiap.techchallenge.notificacao.controllers;

import com.fiap.techchallenge.notificacao.dtos.UsuarioDTO;
import com.fiap.techchallenge.notificacao.dtos.request.AuthRequest;
import com.fiap.techchallenge.notificacao.dtos.response.AuthResponse;
import com.fiap.techchallenge.notificacao.security.JwtTokenProvider;
import com.fiap.techchallenge.notificacao.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import static com.fiap.techchallenge.notificacao.enums.TipoUsuarioEnum.COMUM;
import static com.fiap.techchallenge.notificacao.enums.TipoUsuarioEnum.FISCAL;
import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@RestController
@RequestMapping("/usuario")
@Tag(name = "Usuario", description = "Operações relacionadas a usuários")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UsuarioController(UsuarioService usuarioService, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.usuarioService = usuarioService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/cadastro/comum")
    @Operation(summary = "Registrar um novo usuário comum")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário registrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição")
    })
    public ResponseEntity<UsuarioDTO> registrarUsuarioComum(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        log.info("Iniciando o registro do usuario comum.");
        usuarioDTO.setTipoUsuarioEnum(COMUM);
        UsuarioDTO novoUsuario = usuarioService.registrarUsuario(usuarioDTO);
        log.info("Usuario registrado com sucesso.");
        return ResponseEntity.status(CREATED).body(novoUsuario);
    }

    @PostMapping("/cadastro/fiscal")
    @Operation(summary = "Registrar um novo usuário fiscal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário registrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição")
    })
    public ResponseEntity<UsuarioDTO> registrarUsuarioFiscal(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        log.info("Iniciando o registro do usuario fiscal.");
        usuarioDTO.setTipoUsuarioEnum(FISCAL);
        UsuarioDTO novoUsuario = usuarioService.registrarUsuario(usuarioDTO);
        log.info("Usuario registrado com sucesso.");
        return ResponseEntity.status(CREATED).body(novoUsuario);
    }

    @PostMapping("/login")
    @Operation(summary = "Realizar login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição")
    })
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        String token = usuarioService.login(authRequest.getUsername(), authRequest.getPassword());

        return ResponseEntity.ok(new AuthResponse(token));
    }

    @GetMapping("/validate-token")
    @Operation(summary = "Validar token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token válido"),
            @ApiResponse(responseCode = "401", description = "Token inválido")
    })
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String authorizationHeader) {
        try {
            String token = authorizationHeader.replace("Bearer ", "");

            UsuarioDTO usuarioDTO = usuarioService.validateToken(token);

            return ResponseEntity.ok(usuarioDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token não é válido ou expirou.");
        }
    }
}
