package com.fiap.techchallenge.estacionamentotech.services.impl;

import com.fiap.techchallenge.estacionamentotech.dtos.LocalEstacionamentoDTO;
import com.fiap.techchallenge.estacionamentotech.dtos.VeiculoEstacionadoDTO;
import com.fiap.techchallenge.estacionamentotech.dtos.VoucherEstacionamentoDTO;
import com.fiap.techchallenge.estacionamentotech.entities.LocalEstacionamento;
import com.fiap.techchallenge.estacionamentotech.entities.Usuario;
import com.fiap.techchallenge.estacionamentotech.entities.VeiculoEstacionado;
import com.fiap.techchallenge.estacionamentotech.mappers.LocalEstacionamentoMapper;
import com.fiap.techchallenge.estacionamentotech.mappers.VeiculoEstacionadoMapper;
import com.fiap.techchallenge.estacionamentotech.repositories.LocalEstacionamentoRepository;
import com.fiap.techchallenge.estacionamentotech.repositories.VeiculoEstacionadoRepository;
import com.fiap.techchallenge.estacionamentotech.services.EstacionamentoService;
import com.fiap.techchallenge.estacionamentotech.services.PagamentoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EstacionamentoServiceImpl implements EstacionamentoService {

    private LocalEstacionamentoRepository localEstacionamentoRepository;
    private LocalEstacionamentoMapper localEstacionamentoMapper;
    private VeiculoEstacionadoRepository veiculoEstacionadoRepository;
    private VeiculoEstacionadoMapper veiculoEstacionadoMapper;
    private PagamentoService pagamentoService;

    @Autowired
    public EstacionamentoServiceImpl(LocalEstacionamentoRepository localEstacionamentoRepository,
                                     LocalEstacionamentoMapper localEstacionamentoMapper,
                                     VeiculoEstacionadoRepository veiculoEstacionadoRepository,
                                     VeiculoEstacionadoMapper veiculoEstacionadoMapper,
                                     PagamentoService pagamentoService) {
        this.localEstacionamentoRepository = localEstacionamentoRepository;
        this.localEstacionamentoMapper = localEstacionamentoMapper;
        this.veiculoEstacionadoRepository = veiculoEstacionadoRepository;
        this.veiculoEstacionadoMapper = veiculoEstacionadoMapper;
        this.pagamentoService = pagamentoService;
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

    //TODO tem que ser uma transação para registrar o pagamento e também o estacionamento
    @Override
    public VeiculoEstacionadoDTO registrarEstacionamento(VeiculoEstacionadoDTO veiculoEstacionadoDTO, Usuario usuario) {

        Optional<VeiculoEstacionado> buscaVeiculoEstacionado = veiculoEstacionadoRepository.findByIdVeiculoAndStatusTrue(veiculoEstacionadoDTO.getVeiculoId());

        if (buscaVeiculoEstacionado.isPresent()) {
            VeiculoEstacionado veiculoEstacionadoEncontrado = buscaVeiculoEstacionado.get();

            if(veiculoEstacionadoEncontrado.getIdLocalEstacionamento().equals(veiculoEstacionadoDTO.getLocalEstacionamentoId())){
                throw new RuntimeException("Veiculo já está estacionado neste local, verifique se deseja adicionar mais horas de estacioanemnto.");
            }

        } else {

            VeiculoEstacionado veiculoEstacionado = veiculoEstacionadoMapper.toEntity(veiculoEstacionadoDTO);
            veiculoEstacionado.setIdUsuario(usuario.getIdUsuario());

            veiculoEstacionado = veiculoEstacionadoRepository.save(veiculoEstacionado);
            //TODO ao concluir enviar email com pagamento e veiculo estacionado
            pagamentoService.registrarPagamento(veiculoEstacionado.getIdLocalEstacionamento(),
                                                veiculoEstacionadoDTO.getVoucherEstacionamento().get(0));

        }



        return null;
    }

    @Override
    public VeiculoEstacionadoDTO adicionarHorasDeEstacionamento(Long idVeiculoEstacionado, VoucherEstacionamentoDTO voucherEstacionamentoDTO) {
        //TODO verificar se veículo já está estacionado, ou expirou o prazo, criar um novo estacionamento
        //T0D0 registrar pagamento do estacionamento
        //TODO adicionar voucher ao estacionamento
        return null;
    }
}
