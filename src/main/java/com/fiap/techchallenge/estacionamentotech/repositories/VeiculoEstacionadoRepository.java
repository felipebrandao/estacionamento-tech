package com.fiap.techchallenge.estacionamentotech.repositories;

import com.fiap.techchallenge.estacionamentotech.dtos.EmailEstacionamentoDTO;
import com.fiap.techchallenge.estacionamentotech.entities.VeiculoEstacionado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface VeiculoEstacionadoRepository extends JpaRepository<VeiculoEstacionado, Long> {

    Optional<VeiculoEstacionado> findByIdVeiculoAndStatusTrue(Long veiculoId);

    Optional<VeiculoEstacionado> findByIdVeiculoAndIdLocalEstacionamentoAndStatusTrue(Long idVeiculo,
                                                                                      Long idLocalEstacionamento);

    @Query("SELECT new com.fiap.techchallenge.estacionamentotech.dtos.EmailEstacionamentoDTO(" +
            " u.nome," +
            " u.email," +
            " vei.dataHoraInicio," +
            " le.logradouro," +
            " le.bairro," +
            " le.cep," +
            " le.intervaloDeNumero," +
            " v.marca," +
            " v.modelo," +
            " v.placa) " +
            "FROM VeiculoEstacionado vei " +
            "LEFT JOIN vei.usuario u " +
            "LEFT JOIN vei.veiculo v " +
            "LEFT JOIN vei.localEstacionamento le " +
            "WHERE vei.id = :id")
    EmailEstacionamentoDTO findVeiculoEstacionadoByID(@Param("id") Long id);
}
