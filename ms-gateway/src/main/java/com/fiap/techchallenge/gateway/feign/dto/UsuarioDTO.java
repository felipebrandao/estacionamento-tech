package com.fiap.techchallenge.gateway.feign.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    private String id;
    private String username;
    private String nome;
    private String email;
    private String tipoUsuario;

}
