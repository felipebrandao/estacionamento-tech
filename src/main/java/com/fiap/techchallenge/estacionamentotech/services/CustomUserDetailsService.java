package com.fiap.techchallenge.estacionamentotech.services;

import com.fiap.techchallenge.estacionamentotech.entities.Usuario;
import com.fiap.techchallenge.estacionamentotech.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.fiap.techchallenge.estacionamentotech.enums.TipoUsuarioEnum.COMUM;
import static com.fiap.techchallenge.estacionamentotech.enums.TipoUsuarioEnum.FISCAL;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        if (usuario.getTipoUsuarioEnum() == COMUM) {
            authorities.add(new SimpleGrantedAuthority("COMUM"));
        } else if (usuario.getTipoUsuarioEnum() == FISCAL) {
            authorities.add(new SimpleGrantedAuthority("FISCAL"));
        }

        return new User(usuario.getEmail(), usuario.getSenha(), authorities);
    }
}

