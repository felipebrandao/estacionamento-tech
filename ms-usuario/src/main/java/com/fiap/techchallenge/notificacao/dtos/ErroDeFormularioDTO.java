package com.fiap.techchallenge.notificacao.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErroDeFormularioDTO {

    private String campo;
    private String erro;

}
