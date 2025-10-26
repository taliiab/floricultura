package br.csi.floricultura.model.pessoa;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "pessoa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Schema(description = "Entidade que representa uma pessoa no sistema")
public class Pessoa implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Id da pessoa", example = "1")
    private Long id;

    @UuidGenerator
    private UUID uuid;

    @NonNull
    @NotBlank(message = "O nome não pode ser vazio")
    @Schema(description = "Nome da pessoa", example = "Fulano")
    private String nome;

    @NonNull
    @NotBlank(message = "O CPF não pode ser vazio")
    @Schema(description = "CPF da pessoa", example = "12345678901")
    private String cpf;

    @NonNull
    @Email
    @Schema(description = "Email da pessoa", example = "fulano@email.com")
    private String email;

    @NonNull
    @NotBlank(message = "A senha não pode ser vazia")
    @Schema(description = "Senha da pessoa (hash)", example = "$2a$10$...")
    private String senha;

    @Embedded
    @NonNull
    private Endereco endereco;

    @Schema(description = "Permissão/role do usuário", example = "ROLE_USER")
    private String permissao;

    @NonNull
    @Column(name = "ativo")
    private Boolean ativo = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(permissao));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}
