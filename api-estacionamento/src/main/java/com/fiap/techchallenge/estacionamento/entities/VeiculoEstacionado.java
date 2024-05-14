package com.fiap.techchallenge.estacionamento.entities;

import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static jakarta.persistence.GenerationType.IDENTITY;

@Table(name = "veiculoEstacionado")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Builder
public class VeiculoEstacionado {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "id_usuario")
    private UUID idUsuario;

    @Column(name = "id_veiculo")
    private UUID idVeiculo;

    @Column(name = "id_local_estacionamento")
    private UUID idLocalEstacionamento;

    @Column(name = "data_hora_inicio")
    private LocalDateTime dataHoraInicio;

    @Column(name = "status")
    private boolean status = true;

    @OneToMany(mappedBy = "veiculoEstacionado")
    private List<VoucherEstacionamento> voucherEstacionamentoList = new ArrayList<>();

    @Column(name = "data_hora_expira")
    private LocalDateTime dataHoraExpira;

    @Column(name = "notificacao_enviada")
    private boolean notificacaoEnviada = false;

}
