package com.fiap.techchallenge.multa.repositories;

import com.fiap.techchallenge.multa.model.Multa;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface MultaRepository extends MongoRepository<Multa, UUID> {
}
