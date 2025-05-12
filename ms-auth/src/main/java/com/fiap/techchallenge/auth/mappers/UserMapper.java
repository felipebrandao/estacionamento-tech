package com.fiap.techchallenge.auth.mappers;

import com.fiap.techchallenge.auth.dtos.UserDTO;
import com.fiap.techchallenge.auth.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toModel(UserDTO userDTO);
    UserDTO toDTO(User user);
}
