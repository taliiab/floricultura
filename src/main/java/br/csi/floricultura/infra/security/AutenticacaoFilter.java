package br.csi.floricultura.infra.security;

import br.csi.floricultura.service.AutenticacaoService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AutenticacaoFilter extends OncePerRequestFilter {

    private final TokenServiceJWT tokenServiceJWT;
    private final AutenticacaoService autenticacaoService;

    public AutenticacaoFilter(TokenServiceJWT tokenServiceJWT, AutenticacaoService autenticacaoService) {
        this.tokenServiceJWT = tokenServiceJWT;
        this.autenticacaoService = autenticacaoService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        System.out.println("Filtro para autenticação e autorização");

        String token = recuperarToken(request);
        System.out.println("Token: " + token);

        if (token != null) {
            try {
                String subject = this.tokenServiceJWT.getSubject(token);
                System.out.println("Login: " + subject);

                UserDetails userDetails = this.autenticacaoService.loadUserByUsername(subject);

                if (userDetails != null) {
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }

            } catch (RuntimeException e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Token inválido ou expirado");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            return token.substring(7).trim();
        }
        return null;
    }
}
