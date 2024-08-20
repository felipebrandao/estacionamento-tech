package com.fiap.techchallenge.notificacao.model;

import com.fiap.techchallenge.notificacao.enums.TipoUsuarioEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "usuario")
@Data
@Builder
public class Usuario {
    @Id
    private UUID id;
    private String nome;
    private String email;
    private String cpf;
    private TipoUsuarioEnum tipoUsuarioEnum;
    private String senha;

}
