package br.csi.floricultura.infra.security;

import br.csi.floricultura.model.pessoa.Pessoa;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenServiceJWT {

    @Value("${jwt.secret}")
    private String KEY;

    // Gera token JWT com subject e role
    public String gerarToken(Pessoa user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(KEY);
            return JWT.create()
                    .withIssuer("API Floricultura")
                    .withSubject(user.getEmail()) // usar email ou nome
                    .withClaim("ROLE", user.getPermissao()) // ROLE_ADMIN, ROLE_USER etc
                    .withExpiresAt(dataExpiracao())
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new RuntimeException("Erro ao gerar token", e);
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    // Retorna o usuário (subject) do token
    public String getSubject(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(KEY);
            return JWT.require(algorithm)
                    .withIssuer("API Floricultura")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            throw new RuntimeException("Token inválido ou expirado");
        }
    }

    // Retorna a role do token
    public String getRole(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(KEY);
            return JWT.require(algorithm)
                    .withIssuer("API Floricultura")
                    .build()
                    .verify(token)
                    .getClaim("ROLE").asString();
        } catch (JWTVerificationException e) {
            throw new RuntimeException("Token inválido ou expirado");
        }
    }
}
