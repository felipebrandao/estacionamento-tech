package com.fiap.techchallenge.vehicle.mappers;

import com.fiap.techchallenge.vehicle.dtos.VehicleDTO;
import com.fiap.techchallenge.vehicle.model.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VehicleMapper {

    @Mapping(target = "userId", ignore = true)
    Vehicle toModel(VehicleDTO vehicleDTO);
    VehicleDTO toDTO(Vehicle vehicle);

    List<VehicleDTO> toDTOList(List<Vehicle> vehicles);
}
