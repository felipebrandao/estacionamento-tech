package com.fiap.techchallenge.estacionamentotech.controllers;

import com.fiap.techchallenge.estacionamentotech.dtos.UsuarioDTO;
import com.fiap.techchallenge.estacionamentotech.enums.TipoUsuarioEnum;
import com.fiap.techchallenge.estacionamentotech.services.UsuarioService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/cadastro/comum")
    public ResponseEntity<UsuarioDTO> registrarUsuarioComum(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        log.info("Iniciando o registro do usuario comum.");
        usuarioDTO.setTipoUsuarioEnum(TipoUsuarioEnum.COMUM);
        UsuarioDTO novoUsuario = usuarioService.registrarUsuario(usuarioDTO);
        log.info("Usuario registrado com sucesso.");
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    @PostMapping("/cadastro/fiscal")
    public ResponseEntity<UsuarioDTO> registrarUsuarioFiscal(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        log.info("Iniciando o registro do usuario fiscal.");
        usuarioDTO.setTipoUsuarioEnum(TipoUsuarioEnum.FISCAL);
        UsuarioDTO novoUsuario = usuarioService.registrarUsuario(usuarioDTO);
        log.info("Usuario registrado com sucesso.");
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }
}