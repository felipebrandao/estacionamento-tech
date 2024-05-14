package com.fiap.techchallenge.veiculo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErroDeFormularioDTO {

    private String campo;
    private String erro;

}
