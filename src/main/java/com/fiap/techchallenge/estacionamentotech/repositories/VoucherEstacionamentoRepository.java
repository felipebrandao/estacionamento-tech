package com.fiap.techchallenge.estacionamentotech.repositories;

import com.fiap.techchallenge.estacionamentotech.entities.VoucherEstacionamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoucherEstacionamentoRepository extends JpaRepository<VoucherEstacionamento, Long> {

    List<VoucherEstacionamento> findByIdVeiculoEstacionado(Long veiculoEstacionado);
}
