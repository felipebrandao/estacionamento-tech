package com.fiap.techchallenge.estacionamentotech.config;

import com.fiap.techchallenge.estacionamentotech.exceptions.AuthenticationFailedException;
import com.fiap.techchallenge.estacionamentotech.exceptions.EstacionamentoTechException;
import com.fiap.techchallenge.estacionamentotech.exceptions.InvalidTokenException;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
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
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
@Order(HIGHEST_PRECEDENCE)
@Component
@Slf4j
public class ExceptionHandlerConfig {

    @ExceptionHandler(EstacionamentoTechException.class)
    public ResponseEntity<ErrorDTO> handleEstacionamentoException(EstacionamentoTechException ex) {
        log.error("Erro na requisição, erro: ", ex);
        ErrorDTO error = new ErrorDTO(Instant.now(), BAD_REQUEST.value(), ex.getMessage());
        return ResponseEntity.status(BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<List<ErrorDTO.ErroDeFormularioDTO>> handleException(MethodArgumentNotValidException ex) {
        log.error("Erro na requisição, erro: ", ex);

        List<ErrorDTO.ErroDeFormularioDTO> dto = new ArrayList<>();

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        fieldErrors.forEach(e -> {
            ErrorDTO.ErroDeFormularioDTO erro = new ErrorDTO.ErroDeFormularioDTO(e.getField(), e.getDefaultMessage());
            dto.add(erro);
        });

        return ResponseEntity.status(BAD_REQUEST).body(dto);
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ErrorDTO> handleInvalidTokenException(InvalidTokenException ex) {
        log.error("Erro na requisição, erro: ", ex);
        ErrorDTO error = new ErrorDTO(Instant.now(), UNAUTHORIZED.value(), ex.getMessage());
        return ResponseEntity.status(UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(AuthenticationFailedException.class)
    public ResponseEntity<String> handleAuthenticationFailedException(AuthenticationFailedException ex) {
        return ResponseEntity.status(UNAUTHORIZED).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorDTO> handleException(Exception ex) {
        log.error("Erro na requisição, erro: ", ex);
        ErrorDTO error = new ErrorDTO(Instant.now(), INTERNAL_SERVER_ERROR.value(), "Ocorreu um erro inesperado");
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(error);
    }

    @Getter
    @Setter
    public static class ErrorDTO {
        private Instant timestamp;
        private int status;
        private String mensagem;

        public ErrorDTO(Instant timestamp, int status, String mensagem) {
            this.timestamp = timestamp;
            this.status = status;
            this.mensagem = mensagem;
        }

        @Getter
        @Setter
        public static class ErroDeFormularioDTO {

            private String campo;
            private String erro;

            public ErroDeFormularioDTO(String campo, String erro) {
                this.campo = campo;
                this.erro = erro;
            }
        }
    }
}
