package com.fiap.techchallenge.vehicle.controllers;

import com.fiap.techchallenge.vehicle.dtos.VehicleDTO;
import com.fiap.techchallenge.vehicle.services.VehicleService;
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
@RequestMapping("/vehicle")
@Tag(name = "Veículo")
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping
    @Operation(summary = "Cadastrar veículo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Veículo cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição")
    })
    public ResponseEntity<VehicleDTO> registerVehicle(@RequestBody VehicleDTO vehicleDTO) {
        VehicleDTO registeredVehicle = vehicleService.registerVehicle(vehicleDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredVehicle );
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar veículo por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Veículo encontrado"),
            @ApiResponse(responseCode = "404", description = "Veículo não encontrado")
    })
    public ResponseEntity<VehicleDTO> getVehicleById(@PathVariable UUID id) {
        VehicleDTO vehicle = vehicleService.getVehicleByIdAndUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(vehicle);
    }

    @GetMapping
    @Operation(summary = "Listar veículos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Veículos listados"),
            @ApiResponse(responseCode = "404", description = "Veículos não encontrados")
    })
    public ResponseEntity<List<VehicleDTO>> listVehicles() {
        List<VehicleDTO> vehicles = vehicleService.listVehiclesByUser();
        return ResponseEntity.status(HttpStatus.OK).body(vehicles);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir veículo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Veículo excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Veículo não encontrado")
    })
    public ResponseEntity<Void> deleteVehicle(@PathVariable UUID id) {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar veículo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Veículo atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Veículo não encontrado")
    })
    public ResponseEntity<VehicleDTO> updateVehicle(@PathVariable UUID id, @RequestBody VehicleDTO vehicleDTO) {
        VehicleDTO updatedVehicle = vehicleService.updateVehicle(id, vehicleDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updatedVehicle);
    }
}
