package com.fiap.techchallenge.notificacao.repositories;

import com.fiap.techchallenge.notificacao.entities.VoucherEstacionamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoucherEstacionamentoRepository extends JpaRepository<VoucherEstacionamento, Long> {

    List<VoucherEstacionamento> findByIdVeiculoEstacionado(Long veiculoEstacionado);
}
