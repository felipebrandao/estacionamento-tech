package com.fiap.techchallenge.estacionamentotech.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@NoArgsConstructor
@Table(name = "localEstacionamento")
@Entity
@Data
public class LocalEstacionamento {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = IDENTITY)
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
