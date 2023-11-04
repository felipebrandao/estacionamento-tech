package com.fiap.techchallenge.estacionamentotech.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.techchallenge.estacionamentotech.enums.TipoUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
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
    private TipoUsuario tipoUsuario;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "Senha é um campo obrigatório e não pode estar em branco")
    private String senha;
}
