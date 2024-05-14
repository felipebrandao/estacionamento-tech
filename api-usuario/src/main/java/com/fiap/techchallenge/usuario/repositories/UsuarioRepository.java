package com.fiap.techchallenge.usuario.repositories;

import com.fiap.techchallenge.usuario.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    boolean existsByEmail(String Email);

    Usuario findByEmail(String email);
}
