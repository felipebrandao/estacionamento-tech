package com.fiap.techchallenge.estacionamentoscheduler.repositories;

import com.fiap.techchallenge.estacionamentoscheduler.model.LocalEstacionamento;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface LocalEstacionamentoRepository extends MongoRepository<LocalEstacionamento, UUID> {
}
