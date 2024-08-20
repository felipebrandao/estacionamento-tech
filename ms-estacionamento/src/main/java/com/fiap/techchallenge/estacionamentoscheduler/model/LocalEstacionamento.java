package com.fiap.techchallenge.estacionamentoscheduler.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "localEstacionamento")
@Data
@Builder
public class LocalEstacionamento {
    @Id
    private UUID id;
    private String logradouro;
    private String bairro;
    private String cep;
    private String intervaloDeNumero;
}
