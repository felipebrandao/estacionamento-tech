package com.fiap.techchallenge.usuario.mappers;

import com.fiap.techchallenge.usuario.dtos.UsuarioDTO;
import com.fiap.techchallenge.usuario.entities.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(target = "idUsuario", ignore = true)
    Usuario toEntity(UsuarioDTO usuarioDTO);
    UsuarioDTO toDTO(Usuario usuario);
}
