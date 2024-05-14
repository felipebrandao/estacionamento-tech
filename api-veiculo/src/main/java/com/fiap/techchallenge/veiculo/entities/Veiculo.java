package com.fiap.techchallenge.veiculo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

import static jakarta.persistence.GenerationType.IDENTITY;

@Table(name = "veiculo")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Builder
public class Veiculo {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "marca")
    private String marca;

    @Column(name = "modelo")
    private String modelo;

    @Column(name = "placa")
    private String placa;

    @Column(name = "id_usuario")
    private UUID idUsuario;

}
