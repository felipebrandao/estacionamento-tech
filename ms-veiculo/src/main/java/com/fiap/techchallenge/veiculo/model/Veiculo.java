package com.fiap.techchallenge.veiculo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "veiculo")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Veiculo {

    @Id
    private UUID id;
    private String marca;
    private String modelo;
    private String placa;
    private UUID idUsuario;

}
