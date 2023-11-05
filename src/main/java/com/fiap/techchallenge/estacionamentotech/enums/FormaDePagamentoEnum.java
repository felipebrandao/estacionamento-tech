package com.fiap.techchallenge.estacionamentotech.enums;

public enum FormaDePagamentoEnum {
    DINHEIRO("Dinheiro"),
    CARTAO_CREDITO("Cartão de Crédito"),
    CARTAO_DEBITO("Cartão de Débito"),
    CHEQUE("Cheque"),
    TRANSFERENCIA_BANCARIA("Trasnferência Bancária");

    private String formaDePagamento;

    FormaDePagamentoEnum(String formaDePagamento) {
        this.formaDePagamento = formaDePagamento;
    }

    public String getFormaDePagamento() {
        return formaDePagamento;
    }
}
