package com.fiap.techchallenge.estacionamentotech.repositories;

import com.fiap.techchallenge.estacionamentotech.entities.LocalEstacionamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalEstacionamentoRepository extends JpaRepository<LocalEstacionamento, Long> {
}
