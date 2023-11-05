package com.fiap.techchallenge.estacionamentotech.controllers;

import com.fiap.techchallenge.estacionamentotech.dtos.MultaDTO;
import com.fiap.techchallenge.estacionamentotech.services.MultaService;
import com.fiap.techchallenge.estacionamentotech.utils.UserDetailsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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

    /*
    //TODO Registrar multa
    @PostMapping("/registrar")
    public ResponseEntity<MultaDTO> registrarMulta(@RequestBody MultaDTO multaDto) {
        UserDetails userDetails = userDetailsUtil.getLoggedUserDetails();
        String username = userDetails.getUsername();

        multaDto.setUsuario(username);

        MultaDTO novaMulta = multaService.registrarMulta(multaDto);

        return new ResponseEntity<>(novaMulta, CREATED);
    }
    */
}
