package com.fiap.techchallenge.estacionamentotech.controllers;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    @Autowired
    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @GetMapping("/")
    public ResponseEntity<List<PagamentoDTO>> listarFormaPagamento() {
        List<FormaPagamento> formasPagamento = formaPagamentoService.listarFormasPagamento();
        return ResponseEntity.ok(formasPagamento);
    }

    @PostMapping("/")
    public ResponseEntity<FormaPagamento> cadastrarFormaPagamento(@RequestBody FormaPagamento formaPagamento) {
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
    }
}
