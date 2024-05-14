package com.fiap.techchallenge.usuario.entities;

import com.fiap.techchallenge.usuario.enums.TipoUsuarioEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

@NoArgsConstructor
@Table(name = "usuario")
@Entity
@Data
public class Usuario {

    @Id
    @Column(name = "id")
    private UUID idUsuario;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "tipoUsuario")
    @Enumerated(STRING)
    private TipoUsuarioEnum tipoUsuarioEnum;

    @Column(name = "senha")
    private String senha;

    public Usuario(String nome, String email, String cpf, TipoUsuarioEnum tipoUsuarioEnum, String senha) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.tipoUsuarioEnum = tipoUsuarioEnum;
        this.senha = senha;
    }
}
