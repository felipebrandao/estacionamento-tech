package com.fiap.techchallenge.estacionamento.entities;

import com.fiap.techchallenge.estacionamento.enums.FormaDePagamentoEnum;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Enumerated;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

@Table(name = "Voucher_Estacionamento")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Builder
public class VoucherEstacionamento {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "id_veiculo_estacionado")
    private UUID idVeiculoEstacionado;

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

}
