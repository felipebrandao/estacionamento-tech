package com.fiap.techchallenge.estacionamento.services.impl;

import com.fiap.techchallenge.estacionamento.dtos.LocalEstacionamentoDTO;
import com.fiap.techchallenge.estacionamento.dtos.VeiculoEstacionadoDTO;
import com.fiap.techchallenge.estacionamento.dtos.VoucherEstacionamentoDTO;
import com.fiap.techchallenge.estacionamento.entities.LocalEstacionamento;
import com.fiap.techchallenge.estacionamento.entities.VeiculoEstacionado;
import com.fiap.techchallenge.estacionamento.entities.VoucherEstacionamento;
import com.fiap.techchallenge.estacionamento.mappers.LocalEstacionamentoMapper;
import com.fiap.techchallenge.estacionamento.mappers.VeiculoEstacionadoMapper;
import com.fiap.techchallenge.estacionamento.mappers.VoucherEstacionamentoMapper;
import com.fiap.techchallenge.estacionamento.producer.NotificacaoProducer;
import com.fiap.techchallenge.estacionamento.producer.dto.NotificacaoDTO;
import com.fiap.techchallenge.estacionamento.producer.enums.TipoEmailEnum;
import com.fiap.techchallenge.estacionamento.repositories.LocalEstacionamentoRepository;
import com.fiap.techchallenge.estacionamento.repositories.VeiculoEstacionadoRepository;
import com.fiap.techchallenge.estacionamento.repositories.VoucherEstacionamentoRepository;
import com.fiap.techchallenge.estacionamento.services.EstacionamentoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class EstacionamentoServiceImpl implements EstacionamentoService {

    private final LocalEstacionamentoRepository localEstacionamentoRepository;
    private final LocalEstacionamentoMapper localEstacionamentoMapper;
    private final VeiculoEstacionadoRepository veiculoEstacionadoRepository;
    private final VeiculoEstacionadoMapper veiculoEstacionadoMapper;
    private final NotificacaoProducer notificacaoProducer;
    private final VoucherEstacionamentoMapper voucherEstacionamentoMapper;
    private final VoucherEstacionamentoRepository voucherEstacionamentoRepository;

    @Autowired
    public EstacionamentoServiceImpl(LocalEstacionamentoRepository localEstacionamentoRepository,
                                     LocalEstacionamentoMapper localEstacionamentoMapper,
                                     VeiculoEstacionadoRepository veiculoEstacionadoRepository,
                                     VeiculoEstacionadoMapper veiculoEstacionadoMapper,
                                     NotificacaoProducer notificacaoProducer,
                                     VoucherEstacionamentoMapper voucherEstacionamentoMapper,
                                     VoucherEstacionamentoRepository voucherEstacionamentoRepository) {
        this.localEstacionamentoRepository = localEstacionamentoRepository;
        this.localEstacionamentoMapper = localEstacionamentoMapper;
        this.veiculoEstacionadoRepository = veiculoEstacionadoRepository;
        this.veiculoEstacionadoMapper = veiculoEstacionadoMapper;
        this.notificacaoProducer = notificacaoProducer;
        this.voucherEstacionamentoMapper = voucherEstacionamentoMapper;
        this.voucherEstacionamentoRepository = voucherEstacionamentoRepository;
    }

    @Override
    public LocalEstacionamentoDTO cadastrarLocalEstacionamento(LocalEstacionamentoDTO localDTO) {
        LocalEstacionamento localEstacionamento = localEstacionamentoMapper.toEntity(localDTO);

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

        VeiculoEstacionadoDTO registroVeiculoEstacionadoDTO;

        Optional<VeiculoEstacionado> buscaVeiculoEstacionado =
                veiculoEstacionadoRepository.findByIdVeiculoAndStatusTrue(veiculoEstacionadoDTO.getIdVeiculo());

        if (buscaVeiculoEstacionado.isPresent()) {
            throw new RuntimeException("Veiculo já está estacionado neste local, verifique se deseja adicionar mais horas de estacioanemnto.");
        } else {

            VeiculoEstacionado veiculoEstacionado = veiculoEstacionadoMapper.toEntity(veiculoEstacionadoDTO);
            veiculoEstacionado.setIdUsuario(idUsuario);
            LocalDateTime dataHoraExpira = veiculoEstacionado.getDataHoraInicio().plusHours(veiculoEstacionadoDTO.getVoucherEstacionamento().get(0).getQtdeDeHorasEstacionado());
            veiculoEstacionado.setDataHoraExpira(dataHoraExpira);

            veiculoEstacionado = veiculoEstacionadoRepository.save(veiculoEstacionado);

            VoucherEstacionamentoDTO voucherEstacionamentoDTO = registrarVoucher(veiculoEstacionadoDTO.getVoucherEstacionamento().get(0), veiculoEstacionado.getId());

            registroVeiculoEstacionadoDTO = veiculoEstacionadoMapper.toDTO(veiculoEstacionado);
            registroVeiculoEstacionadoDTO.getVoucherEstacionamento().add(voucherEstacionamentoDTO);

            envioDeEmailEstacionamento(veiculoEstacionado.getId(), TipoEmailEnum.REGISTRO);
        }

        return registroVeiculoEstacionadoDTO;
    }

    private void envioDeEmailEstacionamento(UUID veiculoEstacionado, TipoEmailEnum tipoEmail) {

        NotificacaoDTO notificacaoDTO = NotificacaoDTO.builder()
                .idNotificacao(UUID.randomUUID())
                .idVeiculo(veiculoEstacionado)
                .tipoEmail(tipoEmail)
                .build();

        notificacaoProducer.sendNotificacao(notificacaoDTO);
    }

    private VoucherEstacionamentoDTO registrarVoucher(VoucherEstacionamentoDTO voucherEstacionamentoDTO, UUID veiculoEstacionado) {
        VoucherEstacionamento voucherEstacionamento = voucherEstacionamentoMapper.toEntity(voucherEstacionamentoDTO);
        voucherEstacionamento.setDataHoraRegistro(LocalDateTime.now());
        voucherEstacionamento.setIdVeiculoEstacionado(veiculoEstacionado);

        voucherEstacionamento = voucherEstacionamentoRepository.save(voucherEstacionamento);

        return voucherEstacionamentoMapper.toDTO(voucherEstacionamento);
    }

    @Override
    public VoucherEstacionamentoDTO adicionarHorasDeEstacionamento(UUID idVeiculoEstacionado, VoucherEstacionamentoDTO voucherEstacionamentoDTO) {
        VeiculoEstacionado veiculoEstacionado = veiculoEstacionadoRepository.findById(idVeiculoEstacionado).orElseThrow();

        LocalDateTime dataHoraExpira = veiculoEstacionado.getDataHoraExpira().plusHours(voucherEstacionamentoDTO.getQtdeDeHorasEstacionado());
        veiculoEstacionado.setDataHoraExpira(dataHoraExpira);

        veiculoEstacionado = veiculoEstacionadoRepository.save(veiculoEstacionado);

        VoucherEstacionamentoDTO voucherEstacionamento = registrarVoucher(voucherEstacionamentoDTO, idVeiculoEstacionado);

        envioDeEmailEstacionamento(idVeiculoEstacionado, TipoEmailEnum.REGISTRO);

        return voucherEstacionamento;
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
}
