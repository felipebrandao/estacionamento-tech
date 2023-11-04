package com.fiap.techchallenge.estacionamentotech.services.impl;

import com.fiap.techchallenge.estacionamentotech.exceptions.AuthenticationFailedException;
import com.fiap.techchallenge.estacionamentotech.security.JwtTokenProvider;
import com.fiap.techchallenge.estacionamentotech.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public String login(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        if (authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return jwtTokenProvider.generateToken(userDetails);
        } else {
            throw new AuthenticationFailedException("Falha na autenticação: Email do usuário ou senha inválidos.");
        }
    }
}
