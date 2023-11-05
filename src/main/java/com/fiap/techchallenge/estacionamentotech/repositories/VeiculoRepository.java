package com.fiap.techchallenge.estacionamentotech.repositories;

import com.fiap.techchallenge.estacionamentotech.entities.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    @Query("SELECT v FROM Veiculo v WHERE v.id = :id AND v.usuario.idUsuario = :usuarioId")
    Optional<Veiculo> findByIdAndIdUsuario(@Param("id") Long id, @Param("usuarioId") Long usuarioId);

    List<Veiculo> findByIdUsuario(Long idUsuario);
}
