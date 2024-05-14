package com.fiap.techchallenge.estacionamento.repositories;

import com.fiap.techchallenge.estacionamento.entities.LocalEstacionamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalEstacionamentoRepository extends JpaRepository<LocalEstacionamento, Long> {
}
