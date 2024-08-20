package com.fiap.techchallenge.notificacao.mappers;

import com.fiap.techchallenge.notificacao.dtos.UsuarioDTO;
import com.fiap.techchallenge.notificacao.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toModel(UsuarioDTO usuarioDTO);
    UsuarioDTO toDTO(Usuario usuario);
}
