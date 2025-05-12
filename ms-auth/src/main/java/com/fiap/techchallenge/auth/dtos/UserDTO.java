package com.fiap.techchallenge.auth.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.techchallenge.auth.enums.UserTypeEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;

    @JsonProperty
    @NotBlank(message = "Nome é um campo obrigatório e não pode estar em branco")
    private String name;

    @JsonProperty
    @NotBlank(message="Email é um campo obrigatório e não pode estar em branco")
    @Email(message = "Formato do email é inválido")
    private String email;

    @JsonProperty
    @CPF
    @NotBlank(message = "CPF da Pessoa é um campo obrigatório e não pode estar em branco")
    private String cpf;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UserTypeEnum userType;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "Senha é um campo obrigatório e não pode estar em branco")
    private String password;
}
