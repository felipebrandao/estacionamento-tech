package com.fiap.techchallenge.estacionamentotech.mappers;

import com.fiap.techchallenge.estacionamentotech.dtos.UsuarioDTO;
import com.fiap.techchallenge.estacionamentotech.entities.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(target = "idUsuario", ignore = true)
    Usuario toEntity(UsuarioDTO usuarioDTO);
    UsuarioDTO toDTO(Usuario usuario);
}
