package br.csi.floricultura.controller;

import br.csi.floricultura.model.entrega.Entrega;
import br.csi.floricultura.service.EntregaService;
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
@RequestMapping("/entrega")
@Tag(name = "Entregas", description = "Path relacionado a operações de entregas")
public class EntregaController {

    private final EntregaService service;

    public EntregaController(EntregaService service) {
        this.service = service;
    }

    /* http://localhost:8080/floricultura/entrega/listar */
    @GetMapping("/listar")
    @Operation(summary = "Listar todas as entregas", description = "Retorna a lista completa de entregas cadastradas")
    public List<Entrega> listar() {
        return this.service.listar();
    }

    /* http://localhost:8080/floricultura/entrega/{id} */
    @Operation(summary = "Buscar uma entrega por ID", description = "Busca uma entrega do banco de acordo com o ID informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entrega encontrada",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Entrega.class))),
            @ApiResponse(responseCode = "404", description = "Entrega não encontrada")
    })
    @GetMapping("/buscar/{id}")
    public Entrega getEntregaPorId(@Parameter(description = "Id da entrega a ser buscada") @PathVariable Long id) {
        return this.service.getEntrega(id);
    }

    /* http://localhost:8080/floricultura/entrega */
    @Operation(summary = "Criar uma nova entrega", description = "Cria uma nova entrega no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Entrega criada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Entrega.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    @PostMapping("/criar")
    public void salvar(@RequestBody Entrega entrega) {
        this.service.salvar(entrega);
    }

    /* http://localhost:8080/floricultura/entrega */
    @PutMapping("/atualizar")
    @Operation(summary = "Atualizar uma entrega", description = "Atualiza os dados de uma entrega já cadastrada com base no ID")
    public void atualizar(@RequestBody Entrega entrega) {
        this.service.atualizar(entrega);
    }

    /* http://localhost:8080/floricultura/entrega/{id} */
    @DeleteMapping("/excluir/{id}")
    @Operation(summary = "Excluir uma entrega por ID", description = "Remove uma entrega do banco de acordo com o ID informado")
    public void deletar(@PathVariable Long id) {
        this.service.excluir(id);
    }


}