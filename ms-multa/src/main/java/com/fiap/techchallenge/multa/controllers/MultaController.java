package com.fiap.techchallenge.multa.controllers;

import com.fiap.techchallenge.multa.dtos.MultaDTO;
import com.fiap.techchallenge.multa.services.MultaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@RestController
@RequestMapping("/multa")
public class MultaController {

    private final MultaService multaService;

    @Autowired
    public MultaController(MultaService multaService) {
        this.multaService = multaService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<MultaDTO> registrarMulta(@RequestBody MultaDTO multaDTO, @RequestHeader("id_user") UUID idUsuario) {
        MultaDTO novaMulta = multaService.registrarMulta(multaDTO, idUsuario);
        return new ResponseEntity<>(novaMulta, CREATED);
    }
}
