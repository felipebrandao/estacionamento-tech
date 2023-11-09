package com.fiap.techchallenge.estacionamentotech.controllers;

import com.fiap.techchallenge.estacionamentotech.dtos.MultaDTO;
import com.fiap.techchallenge.estacionamentotech.entities.Usuario;
import com.fiap.techchallenge.estacionamentotech.services.MultaService;
import com.fiap.techchallenge.estacionamentotech.utils.UserDetailsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@RestController
@RequestMapping("/multa")
public class MultaController {

    private final MultaService multaService;
    private final UserDetailsUtil userDetailsUtil;

    @Autowired
    public MultaController(MultaService multaService, UserDetailsUtil userDetailsUtil) {
        this.multaService = multaService;
        this.userDetailsUtil = userDetailsUtil;
    }

    @PostMapping("/registrar")
    public ResponseEntity<MultaDTO> registrarMulta(@RequestBody MultaDTO multaDTO) {
        Usuario usuario = userDetailsUtil.getLoggedUsuario();
        MultaDTO novaMulta = multaService.registrarMulta(multaDTO, usuario);
        return new ResponseEntity<>(novaMulta, CREATED);
    }
}
