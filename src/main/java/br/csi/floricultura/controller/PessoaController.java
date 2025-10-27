package br.csi.floricultura.controller;

import br.csi.floricultura.model.pessoa.DadosPessoa;
import br.csi.floricultura.model.pessoa.Pessoa;
import br.csi.floricultura.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pessoa")
@Tag(name = "Pessoa", description = "Operações de CRUD para Pessoas")
public class PessoaController {

    private final PessoaService service;

    public PessoaController(PessoaService service) {
        this.service = service;
    }

    @Operation(summary = "Registra uma nova pessoa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pessoa criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    @PostMapping("/novo") // alterado de /salvar para /novo para evitar conflito
    public ResponseEntity<DadosPessoa> salvar(@Valid @RequestBody Pessoa pessoa, UriComponentsBuilder uriBuilder) {
        Pessoa pessoaSalva = service.salvar(pessoa);
        DadosPessoa dadosPessoa = new DadosPessoa(pessoaSalva);
        URI uri = uriBuilder.path("/pessoa/{id}").buildAndExpand(pessoaSalva.getId()).toUri();
        return ResponseEntity.created(uri).body(dadosPessoa);
    }

    @Operation(summary = "Lista todas as pessoas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de pessoas retornada com sucesso")
    })
    @GetMapping("/listar")
    public ResponseEntity<List<DadosPessoa>> listar() {
        List<DadosPessoa> pessoas = service.listar().stream().map(DadosPessoa::new).collect(Collectors.toList());
        return ResponseEntity.ok(pessoas);
    }

    @Operation(summary = "Busca uma pessoa por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoa encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
    })
    @GetMapping("/buscar/{id}")
    public ResponseEntity<DadosPessoa> buscarPorId(@PathVariable Long id) {
        Pessoa pessoa = service.getPessoa(id);
        if (pessoa == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new DadosPessoa(pessoa));
    }

    @Operation(summary = "Atualiza uma pessoa por ID")
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<DadosPessoa> atualizar(@PathVariable Long id, @Valid @RequestBody Pessoa pessoa) {
        pessoa.setId(id);
        service.atualizar(pessoa);
        return ResponseEntity.ok(new DadosPessoa(pessoa));
    }

    @Operation(summary = "Excluir uma pessoa por ID")
    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Busca uma pessoa por CPF")
    @GetMapping("/buscar/cpf/{cpf}")
    public ResponseEntity<DadosPessoa> buscarPorCpf(@PathVariable String cpf) {
        Pessoa pessoa = service.getPessoaPorCpf(cpf);
        if (pessoa == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new DadosPessoa(pessoa));
    }

    @Operation(summary = "Busca uma pessoa por email")
    @GetMapping("/buscar/email/{email}")
    public ResponseEntity<DadosPessoa> buscarPorEmail(@PathVariable String email) {
        Pessoa pessoa = service.getPessoaPorEmail(email);
        if (pessoa == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new DadosPessoa(pessoa));
    }
}
