package com.fiap.techchallenge.estacionamento.controllers;

import com.fiap.techchallenge.estacionamento.dtos.LocalEstacionamentoDTO;
import com.fiap.techchallenge.estacionamento.dtos.VeiculoEstacionadoDTO;
import com.fiap.techchallenge.estacionamento.dtos.VoucherEstacionamentoDTO;
import com.fiap.techchallenge.estacionamento.services.EstacionamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/estacionamento")
@Tag(name = "Estacionamento", description = "Operações relacionadas a estacionamento")
public class EstacionamentoController {

    private final EstacionamentoService estacionamentoService;

    @Autowired
    public EstacionamentoController(EstacionamentoService estacionamentoService) {
        this.estacionamentoService = estacionamentoService;
    }

    @PostMapping("/locais")
    @Operation(summary = "Cadastrar locais para estacionamento")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "201", description = "Local cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição")
    })
    public ResponseEntity<LocalEstacionamentoDTO> cadastrarLocaisParaEstacionamento(@RequestBody LocalEstacionamentoDTO localDTO) {
        LocalEstacionamentoDTO localCadastrado = estacionamentoService.cadastrarLocalEstacionamento(localDTO);
        return new ResponseEntity<>(localCadastrado, CREATED);
    }

    @GetMapping("/locais/listar")
    @Operation(summary = "Listar locais para estacionamento")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "Locais listados com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição")
    })
    public ResponseEntity<List<LocalEstacionamentoDTO>> listarLocaisParaEstacionamento() {
        List<LocalEstacionamentoDTO> locais = estacionamentoService.listarLocaisEstacionamento();
        return new ResponseEntity<>(locais, OK);
    }

    @PostMapping("/estacionar")
    @Operation(summary = "Registrar estacionamento de veículo")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "201", description = "Estacionamento registrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição")
    })
    public ResponseEntity<VeiculoEstacionadoDTO> registrarEstacionamento(@RequestBody VeiculoEstacionadoDTO veiculoEstacionadoDTO, @RequestHeader("id_user") UUID idUsuario) {
        VeiculoEstacionadoDTO veiculoEstacionado = estacionamentoService.registrarEstacionamento(veiculoEstacionadoDTO, idUsuario);
        return new ResponseEntity<>(veiculoEstacionado, CREATED);
    }

    @PostMapping("/estender-horas/{idVeiculoEstacionado}")
    @Operation(summary = "Adicionar mais horas de estacionamento")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "Horas adicionadas com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição")
    })
    public ResponseEntity<VoucherEstacionamentoDTO> adicionarMaisHorasDeEstacionamento(@PathVariable UUID idVeiculoEstacionado, @RequestParam VoucherEstacionamentoDTO voucherEstacionamentoDTO) {
        VoucherEstacionamentoDTO voucherEstacionamento = estacionamentoService.adicionarHorasDeEstacionamento(idVeiculoEstacionado, voucherEstacionamentoDTO);
        return new ResponseEntity<>(voucherEstacionamento, OK);
    }
}

