package com.fiap.techchallenge.veiculo.repositories;

import com.fiap.techchallenge.veiculo.entities.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VeiculoRepository extends JpaRepository<Veiculo, UUID> {
    @Query("SELECT v FROM Veiculo v WHERE v.id = :id AND v.idUsuario = :usuarioId")
    Optional<Veiculo> findByIdAndIdUsuario(@Param("id") Long id, @Param("usuarioId") UUID usuarioId);

    List<Veiculo> findByIdUsuario(UUID idUsuario);
}
