package com.fiap.techchallenge.auth.services.impl;

import com.fiap.techchallenge.auth.dtos.UserDTO;
import com.fiap.techchallenge.auth.mappers.UserMapper;
import com.fiap.techchallenge.auth.model.User;
import com.fiap.techchallenge.auth.exceptions.AuthenticationFailedException;
import com.fiap.techchallenge.auth.exceptions.InvalidTokenException;
import com.fiap.techchallenge.auth.repositories.UserRepository;
import com.fiap.techchallenge.auth.security.JwtTokenProvider;
import com.fiap.techchallenge.auth.services.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, UserMapper userMapper, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public String login(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        if (authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return jwtTokenProvider.generateToken(userDetails);
        } else {
            throw new AuthenticationFailedException("Falha na autenticação: Email do usuário ou senha inválidos.");
        }
    }

    @Override
    public UserDTO validateToken(String token) {

        String email = jwtTokenProvider.extractEmail(token);

        if (email == null) {
            throw new InvalidTokenException("Token inválido ou expirado.");
        }

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        return userMapper.toDTO(user);
    }
}
