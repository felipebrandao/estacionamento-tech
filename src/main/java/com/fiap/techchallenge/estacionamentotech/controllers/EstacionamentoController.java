package com.fiap.techchallenge.estacionamentotech.controllers;

import com.fiap.techchallenge.estacionamentotech.dtos.LocalEstacionamentoDTO;
import com.fiap.techchallenge.estacionamentotech.dtos.VeiculoEstacionadoDTO;
import com.fiap.techchallenge.estacionamentotech.services.EstacionamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estacionamento")
public class EstacionamentoController {

    private final EstacionamentoService estacionamentoService;

    @Autowired
    public EstacionamentoController(EstacionamentoService estacionamentoService) {
        this.estacionamentoService = estacionamentoService;
    }

    @PostMapping("/locais")
    public ResponseEntity<LocalEstacionamentoDTO> cadastrarLocaisParaEstacionamento(@RequestBody LocalEstacionamentoDTO localDTO) {
        LocalEstacionamentoDTO localCadastrado = estacionamentoService.cadastrarLocalEstacionamento(localDTO);
        return new ResponseEntity<>(localCadastrado, HttpStatus.CREATED);
    }

    @GetMapping("/locais")
    public ResponseEntity<List<LocalEstacionamentoDTO>> listarLocaisParaEstacionamento() {
        List<LocalEstacionamentoDTO> locais = estacionamentoService.listarLocaisEstacionamento();
        return new ResponseEntity<>(locais, HttpStatus.OK);
    }

    @PostMapping("/estacionar")
    public ResponseEntity<VeiculoEstacionadoDTO> registrarEstacionamento(@RequestBody VeiculoEstacionadoDTO veiculoEstacionadoDTO) {
        VeiculoEstacionadoDTO veiculoEstacionado = estacionamentoService.registrarEstacionamento(veiculoEstacionadoDTO);
        return new ResponseEntity<>(veiculoEstacionado, HttpStatus.CREATED);
    }

    @PostMapping("/estender-horas/{idVeiculoEstacionado}")
    public ResponseEntity<VeiculoEstacionadoDTO> adicionarMaisHorasDeEstacionamento(@PathVariable Long idVeiculoEstacionado, @RequestParam int horas) {
        VeiculoEstacionadoDTO veiculoEstacionado = estacionamentoService.adicionarHorasDeEstacionamento(idVeiculoEstacionado, horas);
        return new ResponseEntity<>(veiculoEstacionado, HttpStatus.OK);
    }

}

