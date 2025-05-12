package com.fiap.techchallenge.vehicle.config;

import com.fiap.techchallenge.vehicle.dtos.FormErrorDTO;
import com.fiap.techchallenge.vehicle.dtos.ErrorDTO;
import com.fiap.techchallenge.vehicle.exceptions.ParkingTechException;
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
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
@Order(HIGHEST_PRECEDENCE)
@Component
@Slf4j
public class ExceptionHandlerConfig {

    @ExceptionHandler(ParkingTechException.class)
    public ResponseEntity<ErrorDTO> handleParkingException(ParkingTechException ex) {
        log.error("Erro na requisição, erro: ", ex);
        ErrorDTO error = new ErrorDTO(Instant.now(), BAD_REQUEST.value(), ex.getMessage());
        return ResponseEntity.status(BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<List<FormErrorDTO>> handleException(MethodArgumentNotValidException ex) {
        log.error("Erro na requisição, erro: ", ex);

        List<FormErrorDTO> dto = new ArrayList<>();

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        fieldErrors.forEach(e -> {
            FormErrorDTO erro = new FormErrorDTO(e.getField(), e.getDefaultMessage());
            dto.add(erro);
        });

        return ResponseEntity.status(BAD_REQUEST).body(dto);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorDTO> handleException(Exception ex) {
        log.error("Erro na requisição, erro: ", ex);
        ErrorDTO error = new ErrorDTO(Instant.now(), INTERNAL_SERVER_ERROR.value(), "Ocorreu um erro inesperado");
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(error);
    }

}
