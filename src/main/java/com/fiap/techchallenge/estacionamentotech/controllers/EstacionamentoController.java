package com.fiap.techchallenge.estacionamentotech.controllers;

import com.fiap.techchallenge.estacionamentotech.dtos.LocalEstacionamentoDTO;
import com.fiap.techchallenge.estacionamentotech.dtos.VeiculoEstacionadoDTO;
import com.fiap.techchallenge.estacionamentotech.dtos.VoucherEstacionamentoDTO;
import com.fiap.techchallenge.estacionamentotech.entities.Usuario;
import com.fiap.techchallenge.estacionamentotech.services.EstacionamentoService;
import com.fiap.techchallenge.estacionamentotech.utils.UserDetailsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/estacionamento")
public class EstacionamentoController {

    private final EstacionamentoService estacionamentoService;

    private final UserDetailsUtil userDetailsUtil;

    @Autowired
    public EstacionamentoController(EstacionamentoService estacionamentoService, UserDetailsUtil userDetailsUtil) {
        this.estacionamentoService = estacionamentoService;
        this.userDetailsUtil = userDetailsUtil;
    }

    @PostMapping("/locais")
    public ResponseEntity<LocalEstacionamentoDTO> cadastrarLocaisParaEstacionamento(@RequestBody LocalEstacionamentoDTO localDTO) {
        LocalEstacionamentoDTO localCadastrado = estacionamentoService.cadastrarLocalEstacionamento(localDTO);
        return new ResponseEntity<>(localCadastrado, CREATED);
    }

    @GetMapping("/locais/listar")
    public ResponseEntity<List<LocalEstacionamentoDTO>> listarLocaisParaEstacionamento() {
        List<LocalEstacionamentoDTO> locais = estacionamentoService.listarLocaisEstacionamento();
        return new ResponseEntity<>(locais, OK);
    }

    @PostMapping("/estacionar")
    public ResponseEntity<VeiculoEstacionadoDTO> registrarEstacionamento(@RequestBody VeiculoEstacionadoDTO veiculoEstacionadoDTO) {
        Usuario usuario = userDetailsUtil.getLoggedUsuario();
        VeiculoEstacionadoDTO veiculoEstacionado = estacionamentoService.registrarEstacionamento(veiculoEstacionadoDTO, usuario);
        return new ResponseEntity<>(veiculoEstacionado, CREATED);
    }

    @PostMapping("/estender-horas/{idVeiculoEstacionado}")
    public ResponseEntity<VeiculoEstacionadoDTO> adicionarMaisHorasDeEstacionamento(@PathVariable Long idVeiculoEstacionado, @RequestParam VoucherEstacionamentoDTO voucherEstacionamentoDTO) {
        VeiculoEstacionadoDTO veiculoEstacionado = estacionamentoService.adicionarHorasDeEstacionamento(idVeiculoEstacionado, voucherEstacionamentoDTO);
        return new ResponseEntity<>(veiculoEstacionado, OK);
    }

}

