package com.fiap.techchallenge.multa.services;

import com.fiap.techchallenge.multa.dtos.MultaDTO;
import com.fiap.techchallenge.multa.model.Usuario;

import java.util.UUID;

public interface MultaService {
    MultaDTO registrarMulta(MultaDTO multaDTO, UUID idUsuario);

}
