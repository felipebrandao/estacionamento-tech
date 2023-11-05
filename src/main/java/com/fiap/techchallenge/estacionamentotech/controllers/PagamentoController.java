package com.fiap.techchallenge.estacionamentotech.controllers;

//@RestController
//@RequestMapping("/pagamento")
public class PagamentoController {
    /*
    private final PagamentoService pagamentoService;

    @Autowired
    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @GetMapping("/")
    public ResponseEntity<List<PagamentoDTO>> listarFormasPagamento() {
        List<FormaPagamento> formasPagamento = formaPagamentoService.listarFormasPagamento();
        return ResponseEntity.ok(formasPagamento);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormaPagamento> buscarFormaPagamentoPorId(@PathVariable Long id) {
        FormaPagamento formaPagamento = formaPagamentoService.buscarFormaPagamentoPorId(id);
        if (formaPagamento != null) {
            return ResponseEntity.ok(formaPagamento);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<FormaPagamento> criarFormaPagamento(@RequestBody FormaPagamento formaPagamento) {
        FormaPagamento novaFormaPagamento = formaPagamentoService.criarFormaPagamento(formaPagamento);
        return ResponseEntity.ok(novaFormaPagamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FormaPagamento> atualizarFormaPagamento(@PathVariable Long id, @RequestBody FormaPagamento formaPagamento) {
        FormaPagamento formaPagamentoAtualizada = formaPagamentoService.atualizarFormaPagamento(id, formaPagamento);
        if (formaPagamentoAtualizada != null) {
            return ResponseEntity.ok(formaPagamentoAtualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirFormaPagamento(@PathVariable Long id) {
        formaPagamentoService.excluirFormaPagamento(id);
        return ResponseEntity.noContent().build();
    }*/
}
