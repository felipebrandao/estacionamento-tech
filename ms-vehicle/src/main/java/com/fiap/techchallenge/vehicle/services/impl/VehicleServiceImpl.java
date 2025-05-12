package com.fiap.techchallenge.vehicle.services.impl;

import com.fiap.techchallenge.vehicle.dtos.VehicleDTO;
import com.fiap.techchallenge.vehicle.exceptions.VehicleException;
import com.fiap.techchallenge.vehicle.mappers.VehicleMapper;
import com.fiap.techchallenge.vehicle.model.Vehicle;
import com.fiap.techchallenge.vehicle.repositories.VehicleRepository;
import com.fiap.techchallenge.vehicle.security.SecurityUtils;
import com.fiap.techchallenge.vehicle.services.VehicleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    private final VehicleMapper vehicleMapper;

    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository, VehicleMapper vehicleMapper) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleMapper = vehicleMapper;
    }

    @Override
    public VehicleDTO registerVehicle(VehicleDTO vehicleDTO) {

        UUID idUsuario = SecurityUtils.getUserId();

        Vehicle vehicle = vehicleMapper.toModel(vehicleDTO);
        vehicle.setId(UUID.randomUUID());
        vehicle.setUserId(idUsuario);

        vehicle = vehicleRepository.save(vehicle);
        return vehicleMapper.toDTO(vehicle);
    }

    @Override
    public List<VehicleDTO> listVehiclesByUser() {

        UUID userId = SecurityUtils.getUserId();

        List<Vehicle> vehicles = vehicleRepository.findByUserId(userId);
        if(vehicles.isEmpty()) {
            throw new VehicleException("Nenhum veículo encontrado para o usuário");
        }
        return vehicleMapper.toDTOList(vehicles);
    }

    @Override
    public VehicleDTO getVehicleByIdAndUser(UUID id) {
        UUID userId = SecurityUtils.getUserId();

        Vehicle vehicle = vehicleRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new VehicleException("Veiculo com ID " + id + " não encontrada."));
        return vehicleMapper.toDTO(vehicle);
    }

    @Override
    public void deleteVehicle(UUID id) {

        UUID userId = SecurityUtils.getUserId();

        Vehicle vehicle = vehicleRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new VehicleException("Veiculo com ID " + id + " não encontrado"));

        vehicleRepository.delete(vehicle);
    }

    @Override
    public VehicleDTO updateVehicle(UUID id, VehicleDTO vehicleDTO) {
        UUID userId = SecurityUtils.getUserId();

        Vehicle vehicle = vehicleRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new VehicleException("Veiculo com ID " + id + " não encontrado"));

        vehicle.setBrand(vehicleDTO.getBrand());
        vehicle.setModel(vehicleDTO.getModel());
        vehicle.setLicensePlate(vehicleDTO.getLicensePlate());

        vehicle = vehicleRepository.save(vehicle);
        return vehicleMapper.toDTO(vehicle);
    }
}
