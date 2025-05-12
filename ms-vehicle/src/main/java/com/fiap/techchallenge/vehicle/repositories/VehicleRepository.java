package com.fiap.techchallenge.vehicle.repositories;

import com.fiap.techchallenge.vehicle.model.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VehicleRepository extends MongoRepository<Vehicle, UUID> {
    @Query("{ 'id': :#{#id}, 'userId': :#{#userId} }")
    Optional<Vehicle> findByIdAndUserId(@Param("id") UUID id, @Param("userId") UUID userId);

    List<Vehicle> findByUserId(UUID userId);
}
