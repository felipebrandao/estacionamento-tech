package com.fiap.techchallenge.estacionamentotech.repositories;

import com.fiap.techchallenge.estacionamentotech.dtos.EmailEstacionamentoDTO;
import com.fiap.techchallenge.estacionamentotech.entities.VeiculoEstacionado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
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
            " v.placa," +
            " vei.dataHoraExpira) " +
            "FROM VeiculoEstacionado vei " +
            "LEFT JOIN vei.usuario u " +
            "LEFT JOIN vei.veiculo v " +
            "LEFT JOIN vei.localEstacionamento le " +
            "WHERE vei.id = :id")
    EmailEstacionamentoDTO findVeiculoEstacionadoByID(@Param("id") Long id);


    @Query("SELECT DISTINCT ve " +
            "FROM VeiculoEstacionado ve " +
            "WHERE ve.status = true " +
            "AND ve.notificacaoEnviada = false " +
            "AND ve.dataHoraExpira <= :dataHoraExpiracao")
    List<VeiculoEstacionado> getEstacionamentosPertoDoFim(@Param("dataHoraExpiracao") LocalDateTime dataHoraExpiracao);

    @Query("SELECT DISTINCT ve " +
            "FROM VeiculoEstacionado ve " +
            "WHERE ve.status = true " +
            "AND ve.dataHoraExpira <= :dataHoraExpirado")
    List<VeiculoEstacionado> getEstacionamentosExpirado(@Param("dataHoraExpirado") LocalDateTime dataHoraExpirado);
}
