package com.fiap.techchallenge.estacionamentotech.services.impl;

import com.fiap.techchallenge.estacionamentotech.dtos.EmailEstacionamentoDTO;
import com.fiap.techchallenge.estacionamentotech.dtos.LocalEstacionamentoDTO;
import com.fiap.techchallenge.estacionamentotech.dtos.VeiculoEstacionadoDTO;
import com.fiap.techchallenge.estacionamentotech.dtos.VoucherEstacionamentoDTO;
import com.fiap.techchallenge.estacionamentotech.entities.LocalEstacionamento;
import com.fiap.techchallenge.estacionamentotech.entities.Usuario;
import com.fiap.techchallenge.estacionamentotech.entities.VeiculoEstacionado;
import com.fiap.techchallenge.estacionamentotech.entities.VoucherEstacionamento;
import com.fiap.techchallenge.estacionamentotech.enums.TipoEmailEnum;
import com.fiap.techchallenge.estacionamentotech.mappers.LocalEstacionamentoMapper;
import com.fiap.techchallenge.estacionamentotech.mappers.VeiculoEstacionadoMapper;
import com.fiap.techchallenge.estacionamentotech.mappers.VoucherEstacionamentoMapper;
import com.fiap.techchallenge.estacionamentotech.repositories.LocalEstacionamentoRepository;
import com.fiap.techchallenge.estacionamentotech.repositories.VeiculoEstacionadoRepository;
import com.fiap.techchallenge.estacionamentotech.repositories.VoucherEstacionamentoRepository;
import com.fiap.techchallenge.estacionamentotech.services.EmailService;
import com.fiap.techchallenge.estacionamentotech.services.EstacionamentoService;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EstacionamentoServiceImpl implements EstacionamentoService {

    private final LocalEstacionamentoRepository localEstacionamentoRepository;
    private final LocalEstacionamentoMapper localEstacionamentoMapper;
    private final VeiculoEstacionadoRepository veiculoEstacionadoRepository;
    private final VeiculoEstacionadoMapper veiculoEstacionadoMapper;
    private final EmailService emailService;
    private final VoucherEstacionamentoMapper voucherEstacionamentoMapper;
    private final VoucherEstacionamentoRepository voucherEstacionamentoRepository;

    @Autowired
    public EstacionamentoServiceImpl(LocalEstacionamentoRepository localEstacionamentoRepository,
                                     LocalEstacionamentoMapper localEstacionamentoMapper,
                                     VeiculoEstacionadoRepository veiculoEstacionadoRepository,
                                     VeiculoEstacionadoMapper veiculoEstacionadoMapper,
                                     EmailService emailService,
                                     VoucherEstacionamentoMapper voucherEstacionamentoMapper,
                                     VoucherEstacionamentoRepository voucherEstacionamentoRepository) {
        this.localEstacionamentoRepository = localEstacionamentoRepository;
        this.localEstacionamentoMapper = localEstacionamentoMapper;
        this.veiculoEstacionadoRepository = veiculoEstacionadoRepository;
        this.veiculoEstacionadoMapper = veiculoEstacionadoMapper;
        this.emailService = emailService;
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
    public VeiculoEstacionadoDTO registrarEstacionamento(VeiculoEstacionadoDTO veiculoEstacionadoDTO, Usuario usuario) {

        VeiculoEstacionadoDTO registroVeiculoEstacionadoDTO;

        Optional<VeiculoEstacionado> buscaVeiculoEstacionado =
                veiculoEstacionadoRepository.findByIdVeiculoAndStatusTrue(veiculoEstacionadoDTO.getIdVeiculo());

        if (buscaVeiculoEstacionado.isPresent()) {
            throw new RuntimeException("Veiculo já está estacionado neste local, verifique se deseja adicionar mais horas de estacioanemnto.");
        } else {

            VeiculoEstacionado veiculoEstacionado = veiculoEstacionadoMapper.toEntity(veiculoEstacionadoDTO);
            veiculoEstacionado.setIdUsuario(usuario.getIdUsuario());
            LocalDateTime dataHoraExpira = veiculoEstacionado.getDataHoraInicio().plusHours(veiculoEstacionadoDTO.getVoucherEstacionamento().get(0).getQtdeDeHorasEstacionado());
            veiculoEstacionado.setDataHoraExpira(dataHoraExpira);

            veiculoEstacionado = veiculoEstacionadoRepository.save(veiculoEstacionado);

            VoucherEstacionamentoDTO voucherEstacionamentoDTO = registrarVoucher(veiculoEstacionadoDTO.getVoucherEstacionamento().get(0), veiculoEstacionado.getId());

            registroVeiculoEstacionadoDTO = veiculoEstacionadoMapper.toDTO(veiculoEstacionado);
            registroVeiculoEstacionadoDTO.getVoucherEstacionamento().add(voucherEstacionamentoDTO);

            envioDeEmailEstacionamento(veiculoEstacionado.getId(), "Estacionamento Tech - Registro de Estacionamento", TipoEmailEnum.REGISTRO);
        }

        return registroVeiculoEstacionadoDTO;
    }

    private void envioDeEmailEstacionamento(Long veiculoEstacionado, String assunto, TipoEmailEnum tipoEmail) {
        EmailEstacionamentoDTO emailEstacionamento = veiculoEstacionadoRepository.findVeiculoEstacionadoByID(veiculoEstacionado);
        emailEstacionamento.setAssunto(assunto);
        emailEstacionamento.setTipoEmail(tipoEmail);

        List<VoucherEstacionamento> voucherEstacionamentoList = voucherEstacionamentoRepository.findByIdVeiculoEstacionado(veiculoEstacionado);

        List<VoucherEstacionamentoDTO> voucherEstacionamentoDTOList = voucherEstacionamentoMapper.toDTOList(voucherEstacionamentoList);

        emailEstacionamento.getVoucherEstacionamentoDTOList().addAll(voucherEstacionamentoDTOList);

        try {
            emailService.enviarNotificacaoDeVeiculoEstacionadoEComprovanteDePagamento(emailEstacionamento);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private VoucherEstacionamentoDTO registrarVoucher(VoucherEstacionamentoDTO voucherEstacionamentoDTO, Long veiculoEstacionado) {
        VoucherEstacionamento voucherEstacionamento = voucherEstacionamentoMapper.toEntity(voucherEstacionamentoDTO);
        voucherEstacionamento.setDataHoraRegistro(LocalDateTime.now());
        voucherEstacionamento.setIdVeiculoEstacionado(veiculoEstacionado);

        voucherEstacionamento = voucherEstacionamentoRepository.save(voucherEstacionamento);

        return voucherEstacionamentoMapper.toDTO(voucherEstacionamento);
    }

    @Override
    public VoucherEstacionamentoDTO adicionarHorasDeEstacionamento(Long idVeiculoEstacionado, VoucherEstacionamentoDTO voucherEstacionamentoDTO) {
        VeiculoEstacionado veiculoEstacionado = veiculoEstacionadoRepository.findById(idVeiculoEstacionado).orElseThrow();

        LocalDateTime dataHoraExpira = veiculoEstacionado.getDataHoraExpira().plusHours(voucherEstacionamentoDTO.getQtdeDeHorasEstacionado());
        veiculoEstacionado.setDataHoraExpira(dataHoraExpira);

        veiculoEstacionado = veiculoEstacionadoRepository.save(veiculoEstacionado);

        VoucherEstacionamentoDTO voucherEstacionamento = registrarVoucher(voucherEstacionamentoDTO, idVeiculoEstacionado);

        envioDeEmailEstacionamento(idVeiculoEstacionado, "Estacionamento Tech - Adicionado horas", TipoEmailEnum.REGISTRO);


        return voucherEstacionamento;
    }

    @Override
    public void enviarNotificacoesEstacionamentoEstaPertoDoFim() {

        LocalDateTime dataHoraAtualMenos10min = LocalDateTime.now().minusMinutes(10);
        LocalDateTime dataHoraAtual = LocalDateTime.now();
        
        List<VeiculoEstacionado> estacionamentosPertoDoFim = veiculoEstacionadoRepository.getEstacionamentosPertoDoFim(dataHoraAtualMenos10min, dataHoraAtual);

        for (VeiculoEstacionado estacionado : estacionamentosPertoDoFim) {
            envioDeEmailEstacionamento(estacionado.getIdVeiculo(), "Estacionamento Tech - Aviso de Expiração", TipoEmailEnum.EXPIRACAO);
            estacionado.setNotificacaoEnviada(true);
            veiculoEstacionadoRepository.save(estacionado);
        }
    }

    @Override
    public void estacionamentoExpirado() {
        LocalDateTime dataHoraAtualMais5min = LocalDateTime.now().plusMinutes(5);

        List<VeiculoEstacionado> estacionamentosExpirado = veiculoEstacionadoRepository.getEstacionamentosExpirado(dataHoraAtualMais5min);

        for (VeiculoEstacionado estacionado : estacionamentosExpirado) {
            envioDeEmailEstacionamento(estacionado.getIdVeiculo(), "Estacionamento Tech - Expirado", TipoEmailEnum.EXPIRADO);
            estacionado.setStatus(false);
            veiculoEstacionadoRepository.save(estacionado);
        }
    }
}
