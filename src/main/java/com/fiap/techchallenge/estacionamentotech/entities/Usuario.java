package com.fiap.techchallenge.estacionamentotech.entities;

import com.fiap.techchallenge.estacionamentotech.enums.TipoUsuarioEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Enumerated;

import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

@NoArgsConstructor
@Table(name = "usuario")
@Data
@Entity
public class Usuario {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = IDENTITY)
    private Long idUsuario;

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
