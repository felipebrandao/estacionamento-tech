package com.fiap.techchallenge.estacionamentotech.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailDataDTO {
    private String nomeDoCliente;
    private String to;
    private String subject;
    private String text;
    private Long clienteId;
    private String dataHora;
    private String valorPago;
    private String local;
}
