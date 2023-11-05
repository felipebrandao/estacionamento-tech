package com.fiap.techchallenge.estacionamentotech.utils;

import com.fiap.techchallenge.estacionamentotech.entities.Usuario;
import com.fiap.techchallenge.estacionamentotech.repositories.UsuarioRepository;
import com.fiap.techchallenge.estacionamentotech.services.CustomUserDetailsService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class UserDetailsUtil {

    private final CustomUserDetailsService customUserDetailsService;

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UserDetailsUtil(CustomUserDetailsService customUserDetailsService, UsuarioRepository usuarioRepository) {
        this.customUserDetailsService = customUserDetailsService;
        this.usuarioRepository = usuarioRepository;
    }

    public UserDetails getLoggedUserDetails() {
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public Usuario getLoggedUsuario() {
        UserDetails userDetails = getLoggedUserDetails();
        return usuarioRepository.findByEmail(userDetails.getUsername());
    }

    public String getLoggedUsername() {
        UserDetails userDetails = getLoggedUserDetails();
        return userDetails.getUsername();
    }

}

