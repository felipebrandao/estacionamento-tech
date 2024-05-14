package com.fiap.techchallenge.usuario.services;

import com.fiap.techchallenge.usuario.dtos.UsuarioDTO;

public interface UsuarioService {
    UsuarioDTO registrarUsuario(UsuarioDTO usuarioDTO);

    String login(String username, String password);

    UsuarioDTO validateToken(String token);
}
