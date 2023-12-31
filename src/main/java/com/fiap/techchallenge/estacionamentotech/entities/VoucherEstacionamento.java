package com.fiap.techchallenge.estacionamentotech.entities;

import com.fiap.techchallenge.estacionamentotech.enums.FormaDePagamentoEnum;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Enumerated;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

@Table(name = "voucherEstacionamento")
@NoArgsConstructor
@Entity
@Data
public class VoucherEstacionamento {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "id_veiculo_estacionado")
    private Long idVeiculoEstacionado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_veiculo_estacionado", referencedColumnName = "id", insertable = false, updatable = false)
    private VeiculoEstacionado veiculoEstacionado;

    @Column(name = "data_hora_registro")
    private LocalDateTime dataHoraRegistro;

    @Column(name = "qtde_de_horas_estacionado")
    private Long qtdeDeHorasEstacionado;

    @Column(name = "forma_de_pagamento")
    @Enumerated(STRING)
    private FormaDePagamentoEnum formaDePagamento;

    public VoucherEstacionamento(Long id, Long idVeiculoEstacionado, LocalDateTime dataHoraRegistro, Long qtdeDeHorasEstacionado, FormaDePagamentoEnum formaDePagamento) {
        this.id = id;
        this.idVeiculoEstacionado = idVeiculoEstacionado;
        this.dataHoraRegistro = dataHoraRegistro;
        this.qtdeDeHorasEstacionado = qtdeDeHorasEstacionado;
        this.formaDePagamento = formaDePagamento;
    }
}