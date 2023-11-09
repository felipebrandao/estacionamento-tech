package com.fiap.techchallenge.estacionamentotech.repositories;

import com.fiap.techchallenge.estacionamentotech.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByEmail(String Email);

    Usuario findByEmail(String email);
}
