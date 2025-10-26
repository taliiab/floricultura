package br.csi.floricultura.controller;

import br.csi.floricultura.model.pagamento.Pagamento;
import br.csi.floricultura.service.PagamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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

    /* http://localhost:8080/floricultura/pagamento/listar */
    @GetMapping("/listar")
    @Operation(summary = "Listar todos os pagamentos", description = "Retorna a lista completa de pagamentos cadastrados no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de pagamentos retornada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pagamento.class)))
    })
    public ResponseEntity<List<Pagamento>> listar() {
        List<Pagamento> pagamentos = service.listar();
        return ResponseEntity.ok(pagamentos);
    }

    /* http://localhost:8080/floricultura/pagamento/{id} */
    @GetMapping("/{id}")
    @Operation(summary = "Buscar pagamento por ID", description = "Retorna um pagamento específico com base no ID informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pagamento encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pagamento.class))),
            @ApiResponse(responseCode = "404", description = "Pagamento não encontrado")
    })
    public ResponseEntity<Pagamento> getPagamentoPorId(
            @Parameter(description = "ID do pagamento a ser buscado") @PathVariable Long id) {
        Pagamento pagamento = service.getPagamento(id);
        if (pagamento == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(pagamento);
    }

    /* http://localhost:8080/floricultura/pagamento */
    @PostMapping
    @Operation(summary = "Criar um novo pagamento", description = "Cria um novo pagamento no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pagamento criado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pagamento.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public ResponseEntity<Pagamento> salvar(
            @Parameter(description = "Objeto Pagamento com os dados a serem cadastrados") @RequestBody Pagamento pagamento) {
        Pagamento p = service.salvar(pagamento);
        return ResponseEntity.status(201).body(p);
    }

    /* http://localhost:8080/floricultura/pagamento */
    @PutMapping
    @Operation(summary = "Atualizar um pagamento", description = "Atualiza os dados de um pagamento existente com base no ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pagamento atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pagamento.class))),
            @ApiResponse(responseCode = "404", description = "Pagamento não encontrado")
    })
    public ResponseEntity<Pagamento> atualizar(
            @Parameter(description = "Objeto Pagamento com as novas informações") @RequestBody Pagamento pagamento) {
        Pagamento p = service.atualizar(pagamento);
        return ResponseEntity.ok(p);
    }

    /* http://localhost:8080/floricultura/pagamento/{id} */
    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir um pagamento por ID", description = "Remove permanentemente um pagamento do banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pagamento excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pagamento não encontrado")
    })
    public ResponseEntity<Void> deletar(
            @Parameter(description = "ID do pagamento a ser excluído") @PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

    /* http://localhost:8080/floricultura/pagamento/status/{status} */
    @GetMapping("/status/{status}")
    @Operation(summary = "Listar pagamentos por status", description = "Retorna todos os pagamentos que possuem o status informado (ex: Pago, Pendente)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pagamentos retornados com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pagamento.class))),
            @ApiResponse(responseCode = "404", description = "Nenhum pagamento encontrado com o status informado")
    })
    public ResponseEntity<List<Pagamento>> listarPorStatus(
            @Parameter(description = "Status dos pagamentos (ex: Pago, Pendente)") @PathVariable String status) {
        List<Pagamento> pagamentos = service.listarPorStatus(status);
        return ResponseEntity.ok(pagamentos);
    }
}
