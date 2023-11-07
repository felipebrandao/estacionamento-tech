package com.fiap.techchallenge.estacionamentotech.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.techchallenge.estacionamentotech.enums.TipoUsuarioEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

@NoArgsConstructor
@Data
public class UsuarioDTO {

    @JsonProperty
    @NotBlank(message = "Nome é um campo obrigatório e não pode estar em branco")
    private String nome;

    @JsonProperty
    @NotBlank(message="Email é um campo obrigatório e não pode estar em branco")
    @Email(message = "Formato do email é inválido")
    private String email;

    @JsonProperty
    @CPF
    @NotBlank(message = "CPF da Pessoa é um campo obrigatório e não pode estar em branco")
    private String cpf;

    @JsonIgnore
    private TipoUsuarioEnum tipoUsuarioEnum;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "Senha é um campo obrigatório e não pode estar em branco")
    private String senha;
}
