package com.fiap.techchallenge.vehicle.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormErrorDTO {

    private String field;
    private String error;

}
