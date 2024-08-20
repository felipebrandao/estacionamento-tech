package com.fiap.techchallenge.estacionamentoscheduler.model;

import com.fiap.techchallenge.estacionamentoscheduler.dtos.VoucherEstacionamentoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Document(collection = "veiculoEstacionado")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class VeiculoEstacionado {

    @Id
    private UUID id;
    private UUID idUsuario;
    private UUID idVeiculo;
    private UUID idLocalEstacionamento;
    private LocalDateTime dataHoraInicio;
    private boolean status;
    private List<VoucherEstacionamentoDTO> voucherEstacionamentoList;
    private LocalDateTime dataHoraExpira;
    private boolean notificacaoEnviada;

}
