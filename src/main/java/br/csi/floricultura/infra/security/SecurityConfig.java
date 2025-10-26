package br.csi.floricultura.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


// CORS - cross-origin resource sharing

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AutenticacaoFilter autenticacaoFilter;
    public SecurityConfig(AutenticacaoFilter autenticacaoFilter) {
        this.autenticacaoFilter = autenticacaoFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(crsf -> crsf.disable()) //desabilita alguma coisa = libera endpoint para todos
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                                //endpoint de login - todos
                                .requestMatchers(HttpMethod.POST, "/login").permitAll()
                                //endpoint de registro de pessoa
                                .requestMatchers(HttpMethod.POST, "/pessoa/registrar").permitAll()
                                //documentaçao do swagger
                                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()

                                //deletes - admin
                                .requestMatchers(HttpMethod.DELETE).hasAuthority("ROLE_ADMIN")

                                //post e put em pedido e produto - admin
//                        .requestMatchers(HttpMethod.POST, "/pedido/**", "/produto/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/pedido/**", "/produto/**").hasAuthority("ROLE_ADMIN")
//                        .requestMatchers(HttpMethod.PUT, "/pedido/**", "/produto/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/pedido/**", "/produto/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_USUARIO")

                                //outras requisiçoes - pessoa autenticado
                                .anyRequest().authenticated()
                )
                //executar filtro de token antes do filtro de autenticação do spring
//                .addFilterBefore(this.filtroToken, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(this.autenticacaoFilter, UsernamePasswordAuthenticationFilter.class)
                .build(); // sessao nao salvs estado da pessoa, apenas gera token
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
