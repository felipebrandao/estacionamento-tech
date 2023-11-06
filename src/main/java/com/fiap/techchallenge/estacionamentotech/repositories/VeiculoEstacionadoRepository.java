package com.fiap.techchallenge.estacionamentotech.repositories;

import com.fiap.techchallenge.estacionamentotech.entities.VeiculoEstacionado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface VeiculoEstacionadoRepository extends JpaRepository<VeiculoEstacionado, Long> {

    Optional<VeiculoEstacionado> findByIdVeiculoAndStatusTrue(Long veiculoId);

    Optional<VeiculoEstacionado> findByIdVeiculoAndIdLocalEstacionamentoAndStatusTrue(Long idVeiculo,
                                                                                      Long idLocalEstacionamento);
}
