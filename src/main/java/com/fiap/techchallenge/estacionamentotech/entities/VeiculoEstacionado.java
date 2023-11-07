package com.fiap.techchallenge.estacionamentotech.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "veiculoEstacionado")
@NoArgsConstructor
@Data
public class VeiculoEstacionado {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_usuario")
    private Long idUsuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id", insertable = false, updatable = false)
    private Usuario usuario;

    @Column(name = "id_veiculo")
    private Long idVeiculo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_veiculo", referencedColumnName = "id", insertable = false, updatable = false)
    private Veiculo veiculo;

    @Column(name = "id_local_estacionamento")
    private Long idLocalEstacionamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_local_estacionamento", referencedColumnName = "id", insertable = false, updatable = false)
    private LocalEstacionamento localEstacionamento;

    @Column(name = "data_hora_inicio")
    private LocalDateTime dataHoraInicio;

    @Column(name = "status")
    private boolean status;

    @OneToMany(mappedBy = "veiculoEstacionado")
    private List<VoucherEstacionamento> voucherEstacionamentoList = new ArrayList<>();

    public VeiculoEstacionado(Long id, Long idUsuario, Long idVeiculo, Long idLocalEstacionamento, LocalDateTime dataHoraInicio, boolean status) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idVeiculo = idVeiculo;
        this.idLocalEstacionamento = idLocalEstacionamento;
        this.dataHoraInicio = dataHoraInicio;
        this.status = status;
    }
}
