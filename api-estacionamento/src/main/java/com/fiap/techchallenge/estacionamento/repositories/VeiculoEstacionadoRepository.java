package com.fiap.techchallenge.estacionamento.repositories;

import com.fiap.techchallenge.estacionamento.entities.VeiculoEstacionado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VeiculoEstacionadoRepository extends JpaRepository<VeiculoEstacionado, UUID> {

    Optional<VeiculoEstacionado> findByIdVeiculoAndStatusTrue(UUID veiculoId);

    @Query("SELECT ve " +
            "FROM VeiculoEstacionado ve " +
            "WHERE ve.idVeiculo = :idVeiculo " +
            "AND ve.idLocalEstacionamento = :idLocalEstacionamento " +
            "AND ve.status = true")
    VeiculoEstacionado findByIdVeiculoAndIdLocalEstacionamentoAndStatusTrue(@Param("idVeiculo") UUID idVeiculo,
                                                                            @Param("idLocalEstacionamento") UUID idLocalEstacionamento);

//    @Query("SELECT new com.fiap.techchallenge.usuario.dtos.EmailEstacionamentoDTO(" +
//            " u.nome," +
//            " u.email," +
//            " vei.dataHoraInicio," +
//            " le.logradouro," +
//            " le.bairro," +
//            " le.cep," +
//            " le.intervaloDeNumero," +
//            " v.marca," +
//            " v.modelo," +
//            " v.placa," +
//            " vei.dataHoraExpira) " +
//            "FROM VeiculoEstacionado vei " +
//            "LEFT JOIN vei.usuario u " +
//            "LEFT JOIN vei.veiculo v " +
//            "LEFT JOIN vei.localEstacionamento le " +
//            "WHERE vei.id = :id")
//    EmailEstacionamentoDTO findVeiculoEstacionadoByID(@Param("id") UUID id);


    @Query("SELECT DISTINCT ve " +
            "FROM VeiculoEstacionado ve " +
            "WHERE ve.status = true " +
            "AND ve.notificacaoEnviada = false " +
            "AND ve.dataHoraExpira >= :dataHoraAtualMenos10min " +
            "AND ve.dataHoraExpira <= :dataHoraAtual")
    List<VeiculoEstacionado> getEstacionamentosPertoDoFim(@Param("dataHoraAtualMenos10min") LocalDateTime dataHoraAtualMenos10min, @Param("dataHoraAtual") LocalDateTime dataHoraAtual);

    @Query("SELECT DISTINCT ve " +
            "FROM VeiculoEstacionado ve " +
            "WHERE ve.status = true " +
            "AND ve.dataHoraExpira >= :dataHoraAtualMais5min")
    List<VeiculoEstacionado> getEstacionamentosExpirado(@Param("dataHoraAtualMais5min") LocalDateTime dataHoraAtualMais5min);
}
