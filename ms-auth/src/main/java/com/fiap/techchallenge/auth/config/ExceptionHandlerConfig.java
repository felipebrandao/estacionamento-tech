package com.fiap.techchallenge.auth.config;

import com.fiap.techchallenge.auth.dtos.FormErrorDTO;
import com.fiap.techchallenge.auth.dtos.ErrorDTO;
import com.fiap.techchallenge.auth.exceptions.AuthenticationFailedException;
import com.fiap.techchallenge.auth.exceptions.ParkingTechException;
import com.fiap.techchallenge.auth.exceptions.InvalidTokenException;
import com.fiap.techchallenge.auth.exceptions.UserAlreadyExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;
import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
@Order(HIGHEST_PRECEDENCE)
@Component
@Slf4j
public class ExceptionHandlerConfig {

    @ExceptionHandler(ParkingTechException.class)
    public ResponseEntity<ErrorDTO> handleParkingException(ParkingTechException ex) {
        log.error("Erro de requisição: ", ex);
        ErrorDTO error = new ErrorDTO(Instant.now(), BAD_REQUEST.value(), ex.getMessage());
        return ResponseEntity.status(BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<List<FormErrorDTO>> handleException(MethodArgumentNotValidException ex) {
        log.error("Erro de validação: ", ex);

        List<FormErrorDTO> dto = new ArrayList<>();

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        fieldErrors.forEach(e -> {
            FormErrorDTO error = new FormErrorDTO(e.getField(), e.getDefaultMessage());
            dto.add(error);
        });

        return ResponseEntity.status(BAD_REQUEST).body(dto);
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ErrorDTO> handleInvalidTokenException(InvalidTokenException ex) {
        log.error("Token inválido: ", ex);
        ErrorDTO error = new ErrorDTO(Instant.now(), UNAUTHORIZED.value(), ex.getMessage());
        return ResponseEntity.status(UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(AuthenticationFailedException.class)
    public ResponseEntity<ErrorDTO> handleAuthenticationFailedException(AuthenticationFailedException ex) {
        log.error("Autenticação falhou: ", ex);
        ErrorDTO error = new ErrorDTO(Instant.now(), UNAUTHORIZED.value(), ex.getMessage());
        return ResponseEntity.status(UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorDTO> handleUsuarioExisteException(UserAlreadyExistsException ex) {
        log.error("Usuário já existe: ", ex);
        ErrorDTO error = new ErrorDTO(Instant.now(), UNAUTHORIZED.value(), ex.getMessage());
        return ResponseEntity.status(UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorDTO> handleBadCredentialsException(BadCredentialsException ex) {
        log.error("Credenciais inválidas: ", ex);
        ErrorDTO error = new ErrorDTO(Instant.now(), UNAUTHORIZED.value(), ex.getMessage());
        return ResponseEntity.status(UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorDTO> handleException(Exception ex) {
        log.error("Erro inesperado: ", ex);
        ErrorDTO error = new ErrorDTO(Instant.now(), INTERNAL_SERVER_ERROR.value(), "Um erro inesperado ocorreu");
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(error);
    }

}
