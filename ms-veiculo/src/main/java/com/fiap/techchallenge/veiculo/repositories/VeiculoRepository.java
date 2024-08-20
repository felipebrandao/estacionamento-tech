package com.fiap.techchallenge.veiculo.repositories;

import com.fiap.techchallenge.veiculo.model.Veiculo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VeiculoRepository extends MongoRepository<Veiculo, UUID> {
    @Query("{ 'id': :#{#id}, 'idUsuario': :#{#usuarioId} }")
    Optional<Veiculo> findByIdAndIdUsuario(@Param("id") Long id, @Param("usuarioId") UUID usuarioId);

    List<Veiculo> findByIdUsuario(UUID idUsuario);
}
