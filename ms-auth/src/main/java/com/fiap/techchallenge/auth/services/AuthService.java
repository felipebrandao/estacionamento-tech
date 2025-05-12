package com.fiap.techchallenge.auth.services;

import com.fiap.techchallenge.auth.dtos.UserDTO;

public interface AuthService {

    String login(String username, String password);

    UserDTO validateToken(String token);
}
