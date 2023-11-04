package com.fiap.techchallenge.estacionamentotech.entities;

import com.fiap.techchallenge.estacionamentotech.enums.TipoUsuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuario")
@NoArgsConstructor
@Getter
@Setter
public class Usuario {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "tipoUsuario")
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;

    @Column(name = "senha")
    private String senha;

    public Usuario(String nome, String email, String cpf, TipoUsuario tipoUsuario, String senha) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.tipoUsuario = tipoUsuario;
        this.senha = senha;
    }
}
