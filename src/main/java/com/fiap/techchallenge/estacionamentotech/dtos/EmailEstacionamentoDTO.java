package com.fiap.techchallenge.estacionamentotech.dtos;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class EmailEstacionamentoDTO {
    private String assunto;

    private String nomeDoUsuario;
    private String email;

    private LocalDateTime dataHoraInicioEstacionamento;
    private String logradouro;
    private String bairro;
    private String cep;
    private String intervaloDeNumero;

    private String marca;
    private String modelo;
    private String placa;

    private LocalDateTime dataHoraFimEstacionamento;

    private List<VoucherEstacionamentoDTO> voucherEstacionamentoDTOList = new ArrayList<>();

    public EmailEstacionamentoDTO() {
    }

    public EmailEstacionamentoDTO(String nomeDoUsuario,
                                  String email,
                                  LocalDateTime dataHoraInicioEstacionamento,
                                  String logradouro,
                                  String bairro,
                                  String cep,
                                  String intervaloDeNumero,
                                  String marca,
                                  String modelo,
                                  String placa,
                                  LocalDateTime dataHoraFimEstacionamento) {
        this.nomeDoUsuario = nomeDoUsuario;
        this.email = email;
        this.dataHoraInicioEstacionamento = dataHoraInicioEstacionamento;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cep = cep;
        this.intervaloDeNumero = intervaloDeNumero;
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
        this.dataHoraFimEstacionamento = dataHoraFimEstacionamento;
    }
}
