package com.fiap.techchallenge.notificacao.repositories;

import com.fiap.techchallenge.notificacao.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface UsuarioRepository extends MongoRepository<Usuario, UUID> {

    boolean existsByEmail(String Email);

    Usuario findByEmail(String email);
}
