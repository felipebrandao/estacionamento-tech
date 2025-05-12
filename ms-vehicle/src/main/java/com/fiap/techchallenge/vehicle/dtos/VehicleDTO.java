package com.fiap.techchallenge.vehicle.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.UUID;

@Data
public class VehicleDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;
    @JsonProperty
    private String brand;
    @JsonProperty
    private String model;
    @JsonProperty
    private String licensePlate;
}
