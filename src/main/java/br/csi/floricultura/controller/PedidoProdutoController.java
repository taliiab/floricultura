package br.csi.floricultura.controller;

import br.csi.floricultura.model.pedido.PedidoProduto;
import br.csi.floricultura.service.PedidoProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido-produto")
@Tag(name = "PedidoProduto", description = "Path relacionado a operações de itens de pedidos")
public class PedidoProdutoController {

    private final PedidoProdutoService service;

    public PedidoProdutoController(PedidoProdutoService service) {
        this.service = service;
    }

    /* http://localhost:8080/floricultura/pedido-produto/listar */
    @GetMapping("/listar")
    @Operation(summary = "Listar todos os itens de pedidos", description = "Retorna a lista completa de itens de pedidos cadastrados")
    public List<PedidoProduto> listar() {
        return this.service.listar();
    }

    /* http://localhost:8080/floricultura/pedido-produto/{id} */
    @Operation(summary = "Buscar um item de pedido por ID", description = "Busca um item de pedido do banco de acordo com o ID informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item de pedido encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PedidoProduto.class))),
            @ApiResponse(responseCode = "404", description = "Item de pedido não encontrado")
    })
    @GetMapping("/{id}")
    public PedidoProduto getPedidoProdutoPorId(@Parameter(description = "Id do item de pedido a ser buscado") @PathVariable Long id) {
        return this.service.getPedidoProduto(id);
    }

    /* http://localhost:8080/floricultura/pedido-produto/pedido/{pedidoId} */
    @Operation(summary = "Listar itens por pedido", description = "Retorna todos os itens associados a um pedido específico")
    @GetMapping("/pedido/{pedidoId}")
    public List<PedidoProduto> listarPorPedido(@PathVariable Long pedidoId) {
        return this.service.listarPorPedido(pedidoId);
    }

    /* http://localhost:8080/floricultura/pedido-produto */
    @Operation(summary = "Criar um novo item de pedido", description = "Cria um novo item de pedido no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item criado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PedidoProduto.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    @PostMapping
    public void salvar(@RequestBody PedidoProduto pedidoProduto) {
        this.service.salvar(pedidoProduto);
    }

    /* http://localhost:8080/floricultura/pedido-produto */
    @PutMapping
    @Operation(summary = "Atualizar um item de pedido", description = "Atualiza os dados de um item de pedido existente")
    public void atualizar(@RequestBody PedidoProduto pedidoProduto) {
        this.service.atualizar(pedidoProduto);
    }

    /* http://localhost:8080/floricultura/pedido-produto/{id} */
    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir um item de pedido por ID", description = "Remove um item de pedido do banco de acordo com o ID informado")
    public void deletar(@PathVariable Long id) {
        this.service.excluir(id);
    }


}