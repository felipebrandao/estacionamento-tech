package com.fiap.techchallenge.estacionamentotech.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "localEstacionamento")
@NoArgsConstructor
@Data
public class LocalEstacionamento {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "logradouro")
    private String logradouro;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "cep")
    private String cep;

    @Column(name = "intervaloDeNumero")
    private String intervaloDeNumero;

    public LocalEstacionamento(Long id, String logradouro, String bairro, String cep, String intervaloDeNumero) {
        this.id = id;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cep = cep;
        this.intervaloDeNumero = intervaloDeNumero;
    }
}