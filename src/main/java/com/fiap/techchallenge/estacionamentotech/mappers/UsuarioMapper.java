package com.fiap.techchallenge.estacionamentotech.mappers;

import com.fiap.techchallenge.estacionamentotech.dtos.UsuarioDTO;
import com.fiap.techchallenge.estacionamentotech.entities.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioDTO usuarioDTO);
    UsuarioDTO toDTO(Usuario usuario);
}
