package com.fiap.techchallenge.estacionamentoscheduler.repositories;

import com.fiap.techchallenge.estacionamentoscheduler.model.VeiculoEstacionado;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VeiculoEstacionadoRepository extends MongoRepository<VeiculoEstacionado, UUID> {

    @Query("{ 'idVeiculo': :#{#veiculoId}, 'status': true }")
    Optional<VeiculoEstacionado> findByIdVeiculoAndStatusTrue(UUID veiculoId);

    @Query("{ 'idVeiculo': :#{#idVeiculo}, " +
            "'idLocalEstacionamento': :#{#idLocalEstacionamento}, " +
            "'status': true }")
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

    @Query("{ 'status': true, " +
            "'notificacaoEnviada': false, " +
            "'dataHoraExpira': { $gte: :#{#dataHoraAtualMenos10min}, " +
            "$lte: :#{#dataHoraAtual} } }")
    List<VeiculoEstacionado> getEstacionamentosPertoDoFim(@Param("dataHoraAtualMenos10min") LocalDateTime dataHoraAtualMenos10min, @Param("dataHoraAtual") LocalDateTime dataHoraAtual);

    @Query("{ 'status': true, " +
            "'dataHoraExpira': { $gte: :#{#dataHoraAtualMais5min} } }")
    List<VeiculoEstacionado> getEstacionamentosExpirado(@Param("dataHoraAtualMais5min") LocalDateTime dataHoraAtualMais5min);
}
