package br.csi.floricultura.controller;

import br.csi.floricultura.model.pagamento.Pagamento;
import br.csi.floricultura.service.PagamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/pagamento")
@Tag(name = "Pagamentos", description = "Path relacionado a operações de pagamentos")
public class PagamentoController {

    private final PagamentoService service;

    public PagamentoController(PagamentoService service) {
        this.service = service;
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar todos os pagamentos")
    public ResponseEntity<List<Pagamento>> listar() {
        List<Pagamento> pagamentos = service.listar();
        return ResponseEntity.ok(pagamentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pagamento> getPagamentoPorId(@PathVariable Long id) {
        Pagamento pagamento = service.getPagamento(id);
        if (pagamento == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(pagamento);
    }

    @PostMapping
    public ResponseEntity<Pagamento> salvar(@RequestBody Pagamento pagamento) {
        Pagamento p = service.salvar(pagamento);
        return ResponseEntity.status(201).body(p);
    }

    @PutMapping
    public ResponseEntity<Pagamento> atualizar(@RequestBody Pagamento pagamento) {
        Pagamento p = service.atualizar(pagamento);
        return ResponseEntity.ok(p);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Pagamento>> listarPorStatus(@PathVariable String status) {
        List<Pagamento> pagamentos = service.listarPorStatus(status);
        return ResponseEntity.ok(pagamentos);
    }
}
