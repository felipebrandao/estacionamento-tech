package com.fiap.techchallenge.estacionamentotech.utils;

import com.fiap.techchallenge.estacionamentotech.entities.Usuario;
import com.fiap.techchallenge.estacionamentotech.services.CustomUserDetailsService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class UserDetailsUtil {

    private final CustomUserDetailsService customUserDetailsService;

    @Autowired
    public UserDetailsUtil(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    public UserDetails getLoggedUserDetails() {
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public Usuario getLoggedUsuario() {
        UserDetails userDetails = getLoggedUserDetails();
        return (Usuario) customUserDetailsService.loadUserByUsername(userDetails.getUsername());
    }

    public String getLoggedUsername() {
        UserDetails userDetails = getLoggedUserDetails();
        return userDetails.getUsername();
    }
}

