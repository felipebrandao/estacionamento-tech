package com.fiap.techchallenge.multa.services;

import com.fiap.techchallenge.multa.dtos.MultaDTO;
import com.fiap.techchallenge.multa.entities.Usuario;

public interface MultaService {
    MultaDTO registrarMulta(MultaDTO multaDTO, Usuario usuario);

}
