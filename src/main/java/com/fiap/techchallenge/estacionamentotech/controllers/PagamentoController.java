package com.fiap.techchallenge.estacionamentotech.controllers;

import com.fiap.techchallenge.estacionamentotech.dtos.PagamentoDTO;
import com.fiap.techchallenge.estacionamentotech.enums.FormaDePagamento;
import com.fiap.techchallenge.estacionamentotech.services.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    @Autowired
    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @GetMapping("/")
    public ResponseEntity<List<PagamentoDTO>> listarFormasPagamento() {
        List<FormaDePagamento> formasPagamento = pagamentoService.listarFormasPagamento();
        return ResponseEntity.ok(formasPagamento);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormaDePagamento> buscarFormaPagamentoPorId(@PathVariable Long id) {
        FormaDePagamento formaPagamento = pagamentoService.buscarFormaPagamentoPorId(id);
        if (formaPagamento != null) {
            return ResponseEntity.ok(formaPagamento);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<FormaDePagamento> criarFormaPagamento(@RequestBody FormaDePagamento formaPagamento) {
        FormaDePagamento novaFormaPagamento = pagamentoService.criarFormaPagamento(formaPagamento);
        return ResponseEntity.ok(novaFormaPagamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FormaDePagamento> atualizarFormaPagamento(@PathVariable Long id, @RequestBody FormaDePagamento formaPagamento) {
        FormaDePagamento formaPagamentoAtualizada = pagamentoService.atualizarFormaPagamento(id, formaPagamento);
        if (formaPagamentoAtualizada != null) {
            return ResponseEntity.ok(formaPagamentoAtualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirFormaPagamento(@PathVariable Long id) {
        pagamentoService.excluirFormaPagamento(id);
        return ResponseEntity.noContent().build();
    }
}
