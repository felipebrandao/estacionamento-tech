package com.fiap.techchallenge.notificacao.services;

import com.fiap.techchallenge.notificacao.dtos.UsuarioDTO;

public interface UsuarioService {
    UsuarioDTO registrarUsuario(UsuarioDTO usuarioDTO);

    String login(String username, String password);

    UsuarioDTO validateToken(String token);
}
