package com.fiap.techchallenge.multa.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document(collection = "multa")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Multa {

    @Id
    private UUID id;
    private UUID idUsuario;
    private UUID idVeiculo;
    private UUID idLocalEstacionamento;
    private LocalDateTime dataHoraMulta;

}
