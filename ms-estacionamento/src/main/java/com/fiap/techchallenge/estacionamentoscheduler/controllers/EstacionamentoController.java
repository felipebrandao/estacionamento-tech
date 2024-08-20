package com.fiap.techchallenge.estacionamentoscheduler.controllers;

import com.fiap.techchallenge.estacionamentoscheduler.dtos.LocalEstacionamentoDTO;
import com.fiap.techchallenge.estacionamentoscheduler.dtos.VeiculoEstacionadoDTO;
import com.fiap.techchallenge.estacionamentoscheduler.dtos.VoucherEstacionamentoDTO;
import com.fiap.techchallenge.estacionamentoscheduler.services.EstacionamentoService;
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
    public ResponseEntity<VeiculoEstacionadoDTO> registrarEstacionamento(@RequestHeader("id_user") UUID idUsuario, @RequestBody VeiculoEstacionadoDTO veiculoEstacionadoDTO) {
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

    @GetMapping("/veiculo-com-estacionamento/{idVeiculo}")
    @Operation(summary = "Verificar se veículo está com estacionamento ativo")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "Veículo com estacionamento ativo"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição")
    })
    public ResponseEntity<VeiculoEstacionadoDTO> veiculoComEstacionamentoAtivo(@PathVariable UUID idVeiculo) {
        VeiculoEstacionadoDTO veiculoEstacionado = estacionamentoService.veiculoComEstacionamentoAtivo(idVeiculo);
        return new ResponseEntity<>(veiculoEstacionado, OK);
    }

    @GetMapping("/veiculos-estacionados/perto-do-fim")
    @Operation(summary = "Buscar veículos estacionados perto do fim")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "Veículos listados com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição")
    })
    public ResponseEntity<List<VeiculoEstacionadoDTO>> buscarVeiculoEstacionadosPertoDoFim() {
        List<VeiculoEstacionadoDTO> veiculosEstacionados = estacionamentoService.buscarVeiculoEstacionadosPertoDoFim();
        return new ResponseEntity<>(veiculosEstacionados, OK);
    }

    @GetMapping("/veiculos-estacionados/expirados")
    @Operation(summary = "Buscar veículos estacionados expirados")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "Veículos listados com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição")
    })
    public ResponseEntity<List<VeiculoEstacionadoDTO>> buscarVeiculosEstacionadosExpirados() {
        List<VeiculoEstacionadoDTO> veiculosEstacionados = estacionamentoService.buscarVeiculosEstacionadosExpirados();
        return new ResponseEntity<>(veiculosEstacionados, OK);
    }
}

