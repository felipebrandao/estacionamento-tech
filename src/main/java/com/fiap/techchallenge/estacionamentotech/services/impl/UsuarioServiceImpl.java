package com.fiap.techchallenge.estacionamentotech.services.impl;

import com.fiap.techchallenge.estacionamentotech.dtos.UsuarioDTO;
import com.fiap.techchallenge.estacionamentotech.entities.Usuario;
import com.fiap.techchallenge.estacionamentotech.exceptions.UsuarioExisteException;
import com.fiap.techchallenge.estacionamentotech.mappers.UsuarioMapper;
import com.fiap.techchallenge.estacionamentotech.repositories.UsuarioRepository;
import com.fiap.techchallenge.estacionamentotech.services.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UsuarioDTO registrarUsuario(UsuarioDTO usuarioDTO) {
        log.info("Inicio do metódo - UsuarioServiceImpl - registrarUsuario");
        isExisteUsuario(usuarioDTO.getEmail());
        String senhaCriptografada = passwordEncoder.encode(usuarioDTO.getSenha());
        usuarioDTO.setSenha(senhaCriptografada);
        Usuario usuario = usuarioRepository.save(usuarioMapper.toEntity(usuarioDTO));
        log.info("Fim do metódo - UsuarioServiceImpl - registrarUsuario");
        return usuarioMapper.toDTO(usuario);
    }

    private void isExisteUsuario(String email) {
        log.info("Inicio do metódo - UsuarioServiceImpl - isExistePessoa");
        boolean existeUsuario = usuarioRepository.existsByEmail(email);
        if (existeUsuario) {
            throw new UsuarioExisteException("Usuário já cadastrado com este Email.");
        }
        log.info("Fim do metódo - UsuarioServiceImpl - isExistePessoa");
    }
}
