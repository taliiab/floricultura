package br.csi.floricultura.controller;

import br.csi.floricultura.model.pessoa.Pessoa;
import br.csi.floricultura.infra.security.TokenServiceJWT;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@AllArgsConstructor
@Tag(name = "Login", description = "Endpoints relacionados a autenticação")
public class AutenticacaoController {

    private final AuthenticationManager manager;
    private final TokenServiceJWT tokenService;
    private static final Logger logger = LoggerFactory.getLogger(AutenticacaoController.class);

    @Operation(summary = "Fazer login", description = "Autentica o usuário e retorna um token JWT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login bem-sucedido",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DadosToken.class))),
            @ApiResponse(responseCode = "401", description = "Usuário ou senha incorretos"),
            @ApiResponse(responseCode = "500", description = "Erro interno de processamento do login")
    })
    @PostMapping
    public ResponseEntity<?> login(@RequestBody DadosAutenticacao dados) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());

            Authentication authentication = manager.authenticate(authenticationToken);

            Pessoa pessoa = (Pessoa) authentication.getPrincipal();

            String token = tokenService.gerarToken(pessoa);

            return ResponseEntity.ok(new DadosToken(token));

        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Usuário ou senha incorretos");
        } catch (Exception e) {
            logger.error("Erro interno ao processar login: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno de processamento do login");
        }
    }

    public static record DadosAutenticacao(String email, String senha) {}
    public static record DadosToken(String token) {}
}
