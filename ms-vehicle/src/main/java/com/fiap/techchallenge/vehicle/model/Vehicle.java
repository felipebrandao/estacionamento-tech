package com.fiap.techchallenge.vehicle.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "vehicles")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Vehicle {

    @Id
    private UUID id;
    private String brand;
    private String model;
    private String licensePlate;
    private UUID userId;

}
