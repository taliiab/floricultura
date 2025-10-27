package br.csi.floricultura.controller;

import br.csi.floricultura.model.produto.Produto;
import br.csi.floricultura.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/produto")
@Tag(name = "Produtos", description = "Path relacionado a operações de produtos")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar todos os produtos")
    public ResponseEntity<List<Produto>> listar() {
        List<Produto> produtos = service.listar();
        if(produtos.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/buscar/id/{id}")
    @Operation(summary = "Buscar produto por ID")
    public ResponseEntity<Produto> getProdutoPorId(@PathVariable Long id) {
        Produto produto = service.getProduto(id);
        if(produto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(produto);
    }

    @GetMapping("/listar/nome/{nome}")
    @Operation(summary = "Listar produtos por nome")
    public ResponseEntity<List<Produto>> listarPorNome(@PathVariable String nome) {
        List<Produto> produtos = service.listarPorNome(nome);
        if(produtos.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(produtos);
    }

    @PostMapping("/criar")
    @Operation(summary = "Criar um novo produto")
    public ResponseEntity<Produto> salvar(@RequestBody Produto produto, UriComponentsBuilder uriBuilder) {
        Produto p = service.salvar(produto);
        URI uri = uriBuilder.path("/produto/{id}").buildAndExpand(p.getId()).toUri();
        return ResponseEntity.created(uri).body(p);
    }

    @PutMapping("/atualizar/{id}")
    @Operation(summary = "Atualizar um produto")
    public ResponseEntity<Produto> atualizar(@RequestBody Produto produto) {
        Produto p = service.atualizar(produto);
        return ResponseEntity.ok(p);
    }

    @DeleteMapping("/excluir/{id}")
    @Operation(summary = "Excluir um produto por ID")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

}
