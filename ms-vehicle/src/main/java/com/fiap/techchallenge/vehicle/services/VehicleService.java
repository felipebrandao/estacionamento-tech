package com.fiap.techchallenge.vehicle.services;

import com.fiap.techchallenge.vehicle.dtos.VehicleDTO;

import java.util.List;
import java.util.UUID;

public interface VehicleService {
    VehicleDTO registerVehicle(VehicleDTO vehicleDTO);

    List<VehicleDTO> listVehiclesByUser();

    VehicleDTO getVehicleByIdAndUser(UUID id);

    void deleteVehicle(UUID id);

    VehicleDTO updateVehicle(UUID id, VehicleDTO vehicleDTO);
}
