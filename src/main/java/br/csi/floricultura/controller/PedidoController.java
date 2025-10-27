package br.csi.floricultura.controller;

import br.csi.floricultura.model.pedido.Pedido;
import br.csi.floricultura.model.pessoa.Pessoa;
import br.csi.floricultura.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido")
@Tag(name = "Pedidos", description = "Path relacionado a operações de pedidos")
public class PedidoController {

    private final PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar todos os pedidos")
    public List<Pedido> listar() {
        return service.listar();
    }

    @GetMapping("/buscar/{id}")
    @Operation(summary = "Buscar um pedido por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido encontrado"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    })
    public Pedido getPedidoPorId(@Parameter(description = "Id do pedido a ser buscado") @PathVariable Long id) {
        Pedido pedido = service.getPedido(id);
        if (pedido == null) throw new RuntimeException("Pedido não encontrado");
        return pedido;
    }

    @PostMapping("/criar")
    @Operation(summary = "Criar um novo pedido")
    public void salvar(@RequestBody Pedido pedido, @AuthenticationPrincipal Pessoa usuarioLogado) {
        // Passa o usuário logado para o serviço
        service.salvar(pedido, usuarioLogado);
    }

    @PutMapping("/atualizar")
    @Operation(summary = "Atualizar um pedido")
    public void atualizar(@RequestBody Pedido pedido) {
        service.atualizar(pedido);
    }

    @DeleteMapping("/excluir/{id}")
    @Operation(summary = "Excluir um pedido por ID")
    public void deletar(@PathVariable Long id) {
        service.excluir(id);
    }
}
