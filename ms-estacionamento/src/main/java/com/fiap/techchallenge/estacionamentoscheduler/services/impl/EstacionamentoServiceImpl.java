package com.fiap.techchallenge.estacionamentoscheduler.services.impl;

import com.fiap.techchallenge.estacionamentoscheduler.dtos.LocalEstacionamentoDTO;
import com.fiap.techchallenge.estacionamentoscheduler.dtos.VeiculoEstacionadoDTO;
import com.fiap.techchallenge.estacionamentoscheduler.dtos.VoucherEstacionamentoDTO;
import com.fiap.techchallenge.estacionamentoscheduler.exceptions.EstacionamentoTechException;
import com.fiap.techchallenge.estacionamentoscheduler.feign.VeiculoFeignClient;
import com.fiap.techchallenge.estacionamentoscheduler.feign.dto.VeiculoDTO;
import com.fiap.techchallenge.estacionamentoscheduler.mappers.LocalEstacionamentoMapper;
import com.fiap.techchallenge.estacionamentoscheduler.mappers.VeiculoEstacionadoMapper;
import com.fiap.techchallenge.estacionamentoscheduler.model.LocalEstacionamento;
import com.fiap.techchallenge.estacionamentoscheduler.model.VeiculoEstacionado;
import com.fiap.techchallenge.estacionamentoscheduler.producer.NotificacaoProducer;
import com.fiap.techchallenge.estacionamentoscheduler.producer.dto.NotificacaoDTO;
import com.fiap.techchallenge.estacionamentoscheduler.producer.enums.TipoEmailEnum;
import com.fiap.techchallenge.estacionamentoscheduler.repositories.LocalEstacionamentoRepository;
import com.fiap.techchallenge.estacionamentoscheduler.repositories.VeiculoEstacionadoRepository;
import com.fiap.techchallenge.estacionamentoscheduler.services.EstacionamentoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class EstacionamentoServiceImpl implements EstacionamentoService {

    private final LocalEstacionamentoRepository localEstacionamentoRepository;
    private final LocalEstacionamentoMapper localEstacionamentoMapper;
    private final VeiculoEstacionadoRepository veiculoEstacionadoRepository;
    private final VeiculoEstacionadoMapper veiculoEstacionadoMapper;
    private final NotificacaoProducer notificacaoProducer;
    private final VeiculoFeignClient veiculoFeignClient;

    @Autowired
    public EstacionamentoServiceImpl(LocalEstacionamentoRepository localEstacionamentoRepository,
                                     LocalEstacionamentoMapper localEstacionamentoMapper,
                                     VeiculoEstacionadoRepository veiculoEstacionadoRepository,
                                     VeiculoEstacionadoMapper veiculoEstacionadoMapper,
                                     NotificacaoProducer notificacaoProducer,
                                     VeiculoFeignClient veiculoFeignClient) {
        this.localEstacionamentoRepository = localEstacionamentoRepository;
        this.localEstacionamentoMapper = localEstacionamentoMapper;
        this.veiculoEstacionadoRepository = veiculoEstacionadoRepository;
        this.veiculoEstacionadoMapper = veiculoEstacionadoMapper;
        this.notificacaoProducer = notificacaoProducer;
        this.veiculoFeignClient = veiculoFeignClient;
    }

    @Override
    public LocalEstacionamentoDTO cadastrarLocalEstacionamento(LocalEstacionamentoDTO localEstacionamentoDTODTO) {
        LocalEstacionamento localEstacionamento = localEstacionamentoMapper.toModel(localEstacionamentoDTODTO);
        localEstacionamento.setId(UUID.randomUUID());
        localEstacionamento = localEstacionamentoRepository.save(localEstacionamento);
        return localEstacionamentoMapper.toDTO(localEstacionamento);
    }

    @Override
    public List<LocalEstacionamentoDTO> listarLocaisEstacionamento() {
        List<LocalEstacionamento> localEstacionamentoList = localEstacionamentoRepository.findAll();
        return localEstacionamentoMapper.toDTOList(localEstacionamentoList);
    }

    @Transactional
    @Override
    public VeiculoEstacionadoDTO registrarEstacionamento(VeiculoEstacionadoDTO veiculoEstacionadoDTO, UUID idUsuario) {

        Optional<VeiculoEstacionado> buscaVeiculoEstacionado =
                veiculoEstacionadoRepository.findByIdVeiculoAndStatusTrue(veiculoEstacionadoDTO.getIdVeiculo());

        if (buscaVeiculoEstacionado.isPresent()) {
            throw new RuntimeException("Veiculo já está estacionado neste local, verifique se deseja adicionar mais horas de estacionamento.");
        }

        VeiculoDTO veiculoDTO = veiculoFeignClient.buscarVeiculoPorId(idUsuario, veiculoEstacionadoDTO.getIdVeiculo());

        if (veiculoDTO == null) {
            throw new RuntimeException("Veiculo não encontrado.");
        }

        VeiculoEstacionado veiculoEstacionado = VeiculoEstacionado.builder()
                .id(UUID.randomUUID())
                .idVeiculo(veiculoEstacionadoDTO.getIdVeiculo())
                .idLocalEstacionamento(veiculoEstacionadoDTO.getIdLocalEstacionamento())
                .dataHoraInicio(veiculoEstacionadoDTO.getDataHoraInicio())
                .dataHoraExpira(veiculoEstacionadoDTO.getDataHoraInicio().plusHours(veiculoEstacionadoDTO.getVoucherEstacionamento().get(0).getQtdeDeHorasEstacionado()))
                .voucherEstacionamentoList(new ArrayList<>())
                .status(true)
                .build();

        VoucherEstacionamentoDTO voucherEstacionamentoDTO = veiculoEstacionadoDTO.getVoucherEstacionamento().get(0);
        voucherEstacionamentoDTO.setDataHoraRegistro(LocalDateTime.now());

        veiculoEstacionado.getVoucherEstacionamentoList().add(voucherEstacionamentoDTO);
        veiculoEstacionado = veiculoEstacionadoRepository.save(veiculoEstacionado);

        VeiculoEstacionadoDTO registroVeiculoEstacionadoDTO = veiculoEstacionadoMapper.toDTO(veiculoEstacionado);

        envioDeEmailEstacionamento(veiculoEstacionadoDTO, TipoEmailEnum.REGISTRO);

        return registroVeiculoEstacionadoDTO;
    }

    private void envioDeEmailEstacionamento(VeiculoEstacionadoDTO veiculoEstacionado, TipoEmailEnum tipoEmail) {

        var conteudo = Map.of(
                "nome_usuario", veiculoEstacionado,
                "data_hora_inicio", veiculoEstacionado.getDataHoraInicio(),
                "logradouro", veiculoEstacionado.getLocalEstacionamento().getLogradouro(),
                "intervalo_de_numero", veiculoEstacionado.getLocalEstacionamento().getNumero(),
                "bairro", veiculoEstacionado.getLocalEstacionamento().getBairro(),
                "cidade", veiculoEstacionado.getLocalEstacionamento().getCidade(),
                "estado", veiculoEstacionado.getLocalEstacionamento().getEstado(),
                "cep", veiculoEstacionado.getLocalEstacionamento().getCep(),
                "data_hora_expira", veiculoEstacionado.getDataHoraExpira(),
                "qtde_horas_estacionado", veiculoEstacionado.getVoucherEstacionamento().get(0).getQtdeDeHorasEstacionado(),
                "marca", veiculoEstacionado.getMarca(),
                "modelo", veiculoEstacionado.getModelo(),
                "placa", veiculoEstacionado.getPlaca()
        )

        NotificacaoDTO notificacaoDTO = NotificacaoDTO.builder()
                .idNotificacao(UUID.randomUUID())
                .idVeiculo(veiculoEstacionado)
                .tipoEmail(tipoEmail)
                .conteudo(conteudo
                .build();

        notificacaoProducer.sendNotificacao(notificacaoDTO);
    }

    @Override
    public VoucherEstacionamentoDTO adicionarHorasDeEstacionamento(UUID idVeiculoEstacionado, VoucherEstacionamentoDTO voucherEstacionamentoDTO) {
        VeiculoEstacionado veiculoEstacionado = veiculoEstacionadoRepository.findById(idVeiculoEstacionado).orElseThrow();

        veiculoEstacionado.setDataHoraExpira(veiculoEstacionado.getDataHoraExpira().plusHours(voucherEstacionamentoDTO.getQtdeDeHorasEstacionado()));
        voucherEstacionamentoDTO.setDataHoraRegistro(LocalDateTime.now());

        veiculoEstacionado.getVoucherEstacionamentoList().add(voucherEstacionamentoDTO);
        veiculoEstacionado = veiculoEstacionadoRepository.save(veiculoEstacionado);

        envioDeEmailEstacionamento(idVeiculoEstacionado, TipoEmailEnum.REGISTRO);

        return voucherEstacionamentoDTO;
    }

    @Override
    public void enviarNotificacoesEstacionamentoEstaPertoDoFim() {

        LocalDateTime dataHoraAtualMenos10min = LocalDateTime.now().minusMinutes(10);
        LocalDateTime dataHoraAtual = LocalDateTime.now();
        
        List<VeiculoEstacionado> estacionamentosPertoDoFim = veiculoEstacionadoRepository.getEstacionamentosPertoDoFim(dataHoraAtualMenos10min, dataHoraAtual);

        for (VeiculoEstacionado estacionado : estacionamentosPertoDoFim) {
            envioDeEmailEstacionamento(estacionado.getIdVeiculo(), TipoEmailEnum.EXPIRACAO);
            estacionado.setNotificacaoEnviada(true);
            veiculoEstacionadoRepository.save(estacionado);
        }
    }

    @Override
    public void estacionamentoExpirado() {
        LocalDateTime dataHoraAtualMais5min = LocalDateTime.now().plusMinutes(5);

        List<VeiculoEstacionado> estacionamentosExpirado = veiculoEstacionadoRepository.getEstacionamentosExpirado(dataHoraAtualMais5min);

        for (VeiculoEstacionado estacionado : estacionamentosExpirado) {
            envioDeEmailEstacionamento(estacionado.getIdVeiculo(), TipoEmailEnum.EXPIRADO);
            estacionado.setStatus(false);
            veiculoEstacionadoRepository.save(estacionado);
        }
    }

    @Override
    public VeiculoEstacionadoDTO veiculoComEstacionamentoAtivo(UUID idVeiculo) {
        VeiculoEstacionado veiculoEstacionado = veiculoEstacionadoRepository.findByIdVeiculoAndStatusTrue(idVeiculo)
                .orElseThrow(() -> new EstacionamentoTechException("Veículo não está com estacionamento ativo"));
        return veiculoEstacionadoMapper.toDTO(veiculoEstacionado);
    }

    @Override
    public List<VeiculoEstacionadoDTO> buscarVeiculoEstacionadosPertoDoFim() {
        LocalDateTime dataHoraAtualMenos10min = LocalDateTime.now().minusMinutes(10);
        LocalDateTime dataHoraAtual = LocalDateTime.now();

        List<VeiculoEstacionado> estacionamentosPertoDoFim = veiculoEstacionadoRepository.getEstacionamentosPertoDoFim(dataHoraAtualMenos10min, dataHoraAtual);
        return veiculoEstacionadoMapper.toDTOList(estacionamentosPertoDoFim);
    }

    @Override
    public List<VeiculoEstacionadoDTO> buscarVeiculosEstacionadosExpirados() {
        LocalDateTime dataHoraAtualMais5min = LocalDateTime.now().plusMinutes(5);
        List<VeiculoEstacionado> estacionamentosExpirado = veiculoEstacionadoRepository.getEstacionamentosExpirado(dataHoraAtualMais5min);
        return veiculoEstacionadoMapper.toDTOList(estacionamentosExpirado);
    }
}
