package com.fiap.techchallenge.notificacao.services;

import com.fiap.techchallenge.notificacao.dtos.EmailEstacionamentoDTO;
import com.fiap.techchallenge.notificacao.dtos.LocalEstacionamentoDTO;
import com.fiap.techchallenge.notificacao.dtos.VeiculoEstacionadoDTO;
import com.fiap.techchallenge.notificacao.dtos.VoucherEstacionamentoDTO;
import com.fiap.techchallenge.notificacao.model.LocalEstacionamento;
import com.fiap.techchallenge.notificacao.model.Usuario;
import com.fiap.techchallenge.notificacao.model.VeiculoEstacionado;
import com.fiap.techchallenge.notificacao.model.VoucherEstacionamento;
import com.fiap.techchallenge.notificacao.enums.FormaDePagamentoEnum;
import com.fiap.techchallenge.notificacao.enums.TipoEmailEnum;
import com.fiap.techchallenge.notificacao.mappers.LocalEstacionamentoMapper;
import com.fiap.techchallenge.notificacao.mappers.VeiculoEstacionadoMapper;
import com.fiap.techchallenge.notificacao.mappers.VoucherEstacionamentoMapper;
import com.fiap.techchallenge.notificacao.repositories.LocalEstacionamentoRepository;
import com.fiap.techchallenge.notificacao.repositories.VeiculoEstacionadoRepository;
import com.fiap.techchallenge.notificacao.repositories.VoucherEstacionamentoRepository;
import com.fiap.techchallenge.notificacao.services.impl.EstacionamentoServiceImpl;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class EstacionamentoServiceTest {

    private EstacionamentoService estacionamentoService;
    @Mock
    private LocalEstacionamentoRepository localEstacionamentoRepository;

    @Spy
    private LocalEstacionamentoMapper localEstacionamentoMapper= Mappers.getMapper(LocalEstacionamentoMapper.class);

    @Mock
    private VeiculoEstacionadoRepository veiculoEstacionadoRepository;

    @Spy
    private VeiculoEstacionadoMapper veiculoEstacionadoMapper = Mappers.getMapper(VeiculoEstacionadoMapper.class);

    @Mock
    private EmailService emailService;

    @Spy
    private VoucherEstacionamentoMapper voucherEstacionamentoMapper = Mappers.getMapper(VoucherEstacionamentoMapper.class);

    @Mock
    private VoucherEstacionamentoRepository voucherEstacionamentoRepository;

    @BeforeEach
    public void setUp() {
        estacionamentoService = new EstacionamentoServiceImpl(localEstacionamentoRepository,
                                                              localEstacionamentoMapper,
                                                              veiculoEstacionadoRepository,
                                                              veiculoEstacionadoMapper,
                                                              emailService,
                                                              voucherEstacionamentoMapper,
                                                              voucherEstacionamentoRepository);

    }

    private static LocalEstacionamentoDTO obterLocalEstacionamentoDTO() {
        LocalEstacionamentoDTO localEstacionamentoDTO = new LocalEstacionamentoDTO();
        localEstacionamentoDTO.setLogradouro("Teste");
        localEstacionamentoDTO.setBairro("Teste");
        localEstacionamentoDTO.setCep("01234-123");
        localEstacionamentoDTO.setIntervaloDeNumero("1 a 1000");
        return localEstacionamentoDTO;
    }

    private VeiculoEstacionadoDTO obterVeiculoEstacionadoDTO() {
        VeiculoEstacionadoDTO veiculoEstacionadoDTO = new VeiculoEstacionadoDTO();
        veiculoEstacionadoDTO.setIdVeiculo(1L);
        veiculoEstacionadoDTO.setIdLocalEstacionamento(1L);
        veiculoEstacionadoDTO.setDataHoraInicio(LocalDateTime.now());

        List<VoucherEstacionamentoDTO> voucherEstacionamentoDTOList = new ArrayList<>();
        VoucherEstacionamentoDTO voucherEstacionamentoDTO = obterVoucherEstacionamentoDTO();
        voucherEstacionamentoDTOList.add(voucherEstacionamentoDTO);

        veiculoEstacionadoDTO.setVoucherEstacionamento(voucherEstacionamentoDTOList);

        return veiculoEstacionadoDTO;
    }

    private VoucherEstacionamentoDTO obterVoucherEstacionamentoDTO() {
        VoucherEstacionamentoDTO voucherEstacionamentoDTO = new VoucherEstacionamentoDTO();
        voucherEstacionamentoDTO.setFormaDePagamento(FormaDePagamentoEnum.CARTAO_CREDITO);
        voucherEstacionamentoDTO.setQtdeDeHorasEstacionado(1L);

        return voucherEstacionamentoDTO;
    }

    private Usuario obterUsuarioComum() {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1L);
        usuario.setNome("Teste");
        usuario.setEmail("teste@teste.com");

        return usuario;
    }

    private EmailEstacionamentoDTO obterEmailEstacionamentoDTO() {
        EmailEstacionamentoDTO emailEstacionamentoDTO = new EmailEstacionamentoDTO("Teste",
                                                                                   "teste@teste.com",
                                                                                    LocalDateTime.now(),
                                                                                    "teste",
                                                                                    "teste",
                                                                                    "teste",
                                                                                    "teste",
                                                                                    "teste",
                                                                                    "teste",
                                                                                    "teste",
                                                                                    LocalDateTime.now());
        return emailEstacionamentoDTO;
    }

    private VeiculoEstacionado obterVeiculoEstacionado() {
        VeiculoEstacionado estacionado = new VeiculoEstacionado(1L,
                                                                1L,
                                                                1L,
                                                                1L,
                                                                LocalDateTime.now().minusHours(1L),
                                                                true,
                                                                LocalDateTime.now(),
                                                                false);
        return estacionado;
    }

    @Test
    public void testCadastrarLocalEstacionamento() {
        LocalEstacionamentoDTO localEstacionamentoDTO = obterLocalEstacionamentoDTO();

        LocalEstacionamento localEstacionamento = localEstacionamentoMapper.toEntity(localEstacionamentoDTO);

        Mockito.when(localEstacionamentoRepository.save(Mockito.any())).thenReturn(localEstacionamento);

        LocalEstacionamentoDTO result = estacionamentoService.cadastrarLocalEstacionamento(localEstacionamentoDTO);

        assertNotNull(result);
    }

    @Test
    public void testListarLocaisEstacionamento() {
        List<LocalEstacionamento> localEstacionamentoList = new ArrayList<>();
        LocalEstacionamento localEstacionamento = localEstacionamentoMapper.toEntity(obterLocalEstacionamentoDTO());
        localEstacionamentoList.add(localEstacionamento);

        Mockito.when(localEstacionamentoRepository.findAll()).thenReturn(localEstacionamentoList);

        List<LocalEstacionamentoDTO> result = estacionamentoService.listarLocaisEstacionamento();

        assertEquals(1, result.size());
    }

    @Test
    public void testRegistrarEstacionamento() throws MessagingException {

        VeiculoEstacionadoDTO veiculoEstacionadoDTO = obterVeiculoEstacionadoDTO();
        VeiculoEstacionado veiculoEstacionado = veiculoEstacionadoMapper.toEntity(veiculoEstacionadoDTO);
        Usuario usuario = obterUsuarioComum();
        Optional<VeiculoEstacionado> buscaVeiculoEstacionado = Optional.empty();
        VoucherEstacionamento voucherEstacionamento = voucherEstacionamentoMapper.toEntity(obterVoucherEstacionamentoDTO());
        EmailEstacionamentoDTO emailEstacionamentoDTO = obterEmailEstacionamentoDTO();

        Mockito.when(veiculoEstacionadoRepository.findById(veiculoEstacionadoDTO.getIdVeiculo()))
                .thenReturn(buscaVeiculoEstacionado);
        Mockito.when(veiculoEstacionadoMapper.toEntity(veiculoEstacionadoDTO)).thenReturn(veiculoEstacionado);
        Mockito.when(veiculoEstacionadoRepository.save(Mockito.any())).thenReturn(veiculoEstacionado);
        Mockito.when(voucherEstacionamentoRepository.save(Mockito.any())).thenReturn(voucherEstacionamento);
        Mockito.when(veiculoEstacionadoRepository.findVeiculoEstacionadoByID(Mockito.any())).thenReturn(emailEstacionamentoDTO);
        Mockito.doNothing().when(emailService).enviarNotificacaoDeVeiculoEstacionadoEComprovanteDePagamento(Mockito.any());

        VeiculoEstacionadoDTO result = estacionamentoService.registrarEstacionamento(veiculoEstacionadoDTO, usuario);

        assertNotNull(result);
    }


    @Test
    public void testAdicionarHorasDeEstacionamento() throws MessagingException {
        VeiculoEstacionado veiculoEstacionado = veiculoEstacionadoMapper.toEntity(obterVeiculoEstacionadoDTO());
        veiculoEstacionado.setDataHoraExpira(LocalDateTime.now());

        VoucherEstacionamentoDTO voucherEstacionamentoDTO = obterVoucherEstacionamentoDTO();
        VoucherEstacionamento voucherEstacionamento = voucherEstacionamentoMapper.toEntity(voucherEstacionamentoDTO);
        EmailEstacionamentoDTO emailEstacionamentoDTO = obterEmailEstacionamentoDTO();

        Mockito.when(veiculoEstacionadoRepository.findById(Mockito.any())).thenReturn(Optional.of(veiculoEstacionado));
        Mockito.when(veiculoEstacionadoRepository.save(Mockito.any())).thenReturn(veiculoEstacionado);
        Mockito.when(voucherEstacionamentoRepository.save(Mockito.any())).thenReturn(voucherEstacionamento);
        Mockito.when(veiculoEstacionadoRepository.findVeiculoEstacionadoByID(Mockito.any())).thenReturn(emailEstacionamentoDTO);
        Mockito.doNothing().when(emailService).enviarNotificacaoDeVeiculoEstacionadoEComprovanteDePagamento(Mockito.any());

        VoucherEstacionamentoDTO result = estacionamentoService.adicionarHorasDeEstacionamento(1L, voucherEstacionamentoDTO);

        assertNotNull(result);
    }

    @Test
    public void testEnviarNotificacoesEstacionamentoEstaPertoDoFim() throws MessagingException {

        VeiculoEstacionado veiculoEstacionado = new VeiculoEstacionado();
        veiculoEstacionado.setNotificacaoEnviada(false);
        List<VeiculoEstacionado> estacionamentosPertoDoFim = new ArrayList<>();
        estacionamentosPertoDoFim.add(obterVeiculoEstacionado());

        EmailEstacionamentoDTO emailEstacionamentoDTO = obterEmailEstacionamentoDTO();

        Mockito.when(veiculoEstacionadoRepository.getEstacionamentosPertoDoFim(Mockito.any(), Mockito.any()))
                .thenReturn(estacionamentosPertoDoFim);
        Mockito.when(veiculoEstacionadoRepository.save(veiculoEstacionado)).thenReturn(veiculoEstacionado);
        Mockito.when(veiculoEstacionadoRepository.findVeiculoEstacionadoByID(Mockito.any())).thenReturn(emailEstacionamentoDTO);
        Mockito.doNothing().when(emailService).enviarNotificacaoDeVeiculoEstacionadoEComprovanteDePagamento(Mockito.any());

        estacionamentoService.enviarNotificacoesEstacionamentoEstaPertoDoFim();

        for (VeiculoEstacionado estacionado : estacionamentosPertoDoFim) {
            Mockito.verify(emailService).enviarNotificacaoDeVeiculoEstacionadoEComprovanteDePagamento(emailEstacionamentoDTO);
        }

        assertEquals(true, estacionamentosPertoDoFim.get(0).isNotificacaoEnviada());
    }

//
//    @Test
//    public void testEstacionamentoExpirado() {
//        LocalDateTime dataHoraExpirado = LocalDateTime.now().plusMinutes(10);
//        VeiculoEstacionado veiculoEstacionado = new VeiculoEstacionado();
//        veiculoEstacionado.setStatus(true);
//        List<VeiculoEstacionado> estacionamentosExpirado = new ArrayList<>();
//        estacionamentosExpirado.add(veiculoEstacionado);
//
//        Mockito.when(veiculoEstacionadoRepository.getEstacionamentosExpirado(dataHoraExpirado))
//                .thenReturn(estacionamentosExpirado);
//        Mockito.when(veiculoEstacionadoRepository.save(veiculoEstacionado)).thenReturn(veiculoEstacionado);
//        Mockito.when(emailService.enviarNotificacaoDeVeiculoEstacionadoEComprovanteDePagamento(any())).thenReturn(null);
//
//        estacionamentoService.estacionamentoExpirado();
//
//        assertEquals(false, veiculoEstacionado.getStatus());
//    }
}
