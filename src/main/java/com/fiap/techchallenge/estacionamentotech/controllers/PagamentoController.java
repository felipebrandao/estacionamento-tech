package com.fiap.techchallenge.estacionamentotech.controllers;

import com.fiap.techchallenge.estacionamentotech.dtos.PagamentoDTO;
import com.fiap.techchallenge.estacionamentotech.enums.FormaDePagamentoEnum;
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
/*
    @GetMapping("/listar")
    public ResponseEntity<List<PagamentoDTO>> listarFormaPagamento() {
        List<FormaPagamento> formasPagamento = formaPagamentoService.listarFormasPagamento();
        return ResponseEntity.ok(formasPagamento);
    }

    @PostMapping("/")
    public ResponseEntity<FormaPagamento> cadastrarFormaPagamento(@RequestBody FormaPagamento formaPagamento) {
        FormaPagamento novaFormaPagamento = formaPagamentoService.criarFormaPagamento(formaPagamento);
        return ResponseEntity.ok(novaFormaPagamento);
    }

    public ResponseEntity<List<PagamentoDTO>> listarFormasPagamento() {
        List<FormaDePagamentoEnum> formasPagamento = pagamentoService.listarFormasPagamento();
        return ResponseEntity.ok(formasPagamento);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormaDePagamentoEnum> buscarFormaPagamentoPorId(@PathVariable Long id) {
        FormaDePagamentoEnum formaPagamento = pagamentoService.buscarFormaPagamentoPorId(id);
        if (formaPagamento != null) {
            return ResponseEntity.ok(formaPagamento);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<FormaDePagamentoEnum> atualizarFormaPagamento(@PathVariable Long id, @RequestBody FormaDePagamentoEnum formaPagamento) {
        FormaDePagamentoEnum formaPagamentoAtualizada = pagamentoService.atualizarFormaPagamento(id, formaPagamento);
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
    } */
}
