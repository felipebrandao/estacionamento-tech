package com.fiap.techchallenge.estacionamentotech.repositories;

import com.fiap.techchallenge.estacionamentotech.entities.Multa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MultaRepository extends JpaRepository<Multa, Long> {
}
