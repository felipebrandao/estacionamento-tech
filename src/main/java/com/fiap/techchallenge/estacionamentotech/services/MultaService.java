package com.fiap.techchallenge.estacionamentotech.services;

import com.fiap.techchallenge.estacionamentotech.dtos.MultaDTO;
import com.fiap.techchallenge.estacionamentotech.entities.Usuario;

public interface MultaService {
    MultaDTO registrarMulta(MultaDTO multaDTO, Usuario usuario);

}