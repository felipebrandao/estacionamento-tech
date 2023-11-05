package com.fiap.techchallenge.estacionamentotech.entities;

import com.fiap.techchallenge.estacionamentotech.enums.FormaDePagamentoEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "voucherEstacionamento")
@NoArgsConstructor
@Data
public class VoucherEstacionamento {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_veiculo_estacionado")
    private Long idVeiculoEstacionado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_veiculo_estacionado", referencedColumnName = "id", insertable = false, updatable = false)
    private VeiculoEstacionado veiculoEstacionado;

    @Column(name = "data_hora_registro")
    private Long dataHoraRegistro;

    @Column(name = "qtde_de_horas_estacionado")
    private Long qtdeDeHorasEstacionado;

    @Column(name = "formaDePagamento")
    @Enumerated(EnumType.STRING)
    private FormaDePagamentoEnum formaDePagamentoEnum;

    public VoucherEstacionamento(Long id, Long idVeiculoEstacionado, Long dataHoraRegistro, Long qtdeDeHorasEstacionado, FormaDePagamentoEnum formaDePagamentoEnum) {
        this.id = id;
        this.idVeiculoEstacionado = idVeiculoEstacionado;
        this.dataHoraRegistro = dataHoraRegistro;
        this.qtdeDeHorasEstacionado = qtdeDeHorasEstacionado;
        this.formaDePagamentoEnum = formaDePagamentoEnum;
    }
}
