package com.fiap.techchallenge.estacionamento.repositories;

import com.fiap.techchallenge.estacionamento.entities.VoucherEstacionamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoucherEstacionamentoRepository extends JpaRepository<VoucherEstacionamento, Long> {

    List<VoucherEstacionamento> findByIdVeiculoEstacionado(Long veiculoEstacionado);
}
