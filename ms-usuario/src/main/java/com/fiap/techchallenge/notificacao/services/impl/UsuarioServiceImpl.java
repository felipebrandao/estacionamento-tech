package com.fiap.techchallenge.notificacao.services.impl;

import com.fiap.techchallenge.notificacao.dtos.UsuarioDTO;
import com.fiap.techchallenge.notificacao.model.Usuario;
import com.fiap.techchallenge.notificacao.exceptions.AuthenticationFailedException;
import com.fiap.techchallenge.notificacao.exceptions.InvalidTokenException;
import com.fiap.techchallenge.notificacao.exceptions.UsuarioExisteException;
import com.fiap.techchallenge.notificacao.mappers.UsuarioMapper;
import com.fiap.techchallenge.notificacao.repositories.UsuarioRepository;
import com.fiap.techchallenge.notificacao.security.JwtTokenProvider;
import com.fiap.techchallenge.notificacao.services.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public UsuarioDTO registrarUsuario(UsuarioDTO usuarioDTO) {
        isExisteUsuario(usuarioDTO.getEmail());
        String senhaCriptografada = passwordEncoder.encode(usuarioDTO.getSenha());
        usuarioDTO.setSenha(senhaCriptografada);
        usuarioDTO.setId(UUID.randomUUID());
        Usuario usuario = usuarioRepository.save(usuarioMapper.toModel(usuarioDTO));
        return usuarioMapper.toDTO(usuario);
    }

    private void isExisteUsuario(String email) {
        boolean existeUsuario = usuarioRepository.existsByEmail(email);
        if (existeUsuario) {
            throw new UsuarioExisteException("Usuário já cadastrado com este Email.");
        }
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

    @Override
    public UsuarioDTO validateToken(String token) {

        String email = jwtTokenProvider.extractEmail(token);
        if (email != null) {
            Usuario usuario = usuarioRepository.findByEmail(email);
            if(usuario == null){
                throw new UsuarioExisteException("Usuário não encontrado");
            }
            return usuarioMapper.toDTO(usuario);
        } else {
            throw new InvalidTokenException("Token não é válido ou expirou.");
        }
    }
}
