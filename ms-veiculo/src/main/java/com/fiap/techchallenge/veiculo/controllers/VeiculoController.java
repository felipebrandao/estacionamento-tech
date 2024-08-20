package com.fiap.techchallenge.veiculo.controllers;

import com.fiap.techchallenge.veiculo.dtos.VeiculoDTO;
import com.fiap.techchallenge.veiculo.services.VeiculoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/veiculo")
@Tag(name = "Veículo")
public class VeiculoController {

    private final VeiculoService veiculoService;

    @Autowired
    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @PostMapping
    @Operation(summary = "Cadastrar veículo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Veículo cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição")
    })
    public ResponseEntity<VeiculoDTO> cadastrarVeiculo(@RequestBody VeiculoDTO veiculo, @RequestHeader("id_user") UUID idUsuario) {
        VeiculoDTO veiculoCadastrado = veiculoService.cadastrarVeiculo(veiculo, idUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(veiculoCadastrado);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar veículo por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Veículo encontrado"),
            @ApiResponse(responseCode = "404", description = "Veículo não encontrado")
    })
    public ResponseEntity<VeiculoDTO> buscarVeiculoPorId(@PathVariable Long id, @RequestHeader("id_user") UUID idUsuario) {
        VeiculoDTO veiculo = veiculoService.buscarVeiculoPorIdEUsuario(id, idUsuario);
        return ResponseEntity.status(HttpStatus.OK).body(veiculo);
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar veículos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Veículos listados"),
            @ApiResponse(responseCode = "404", description = "Veículos não encontrados")
    })
    public ResponseEntity<List<VeiculoDTO>> listarVeiculos(@RequestHeader("id_user") UUID idUsuario) {
        List<VeiculoDTO> veiculos = veiculoService.listarVeiculosPorUsuario(idUsuario);
        return ResponseEntity.status(HttpStatus.OK).body(veiculos);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir veículo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Veículo excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Veículo não encontrado")
    })
    public ResponseEntity<Void> excluirVeiculo(@PathVariable Long id, @RequestHeader("id_user") UUID idUsuario) {
        veiculoService.excluirVeiculo(id, idUsuario);
        return ResponseEntity.noContent().build();
    }
}
