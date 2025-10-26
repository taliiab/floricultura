package br.csi.floricultura.model.pessoa;

import jakarta.persistence.Embeddable;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Schema(description = "Entidade que representa o endereço de uma pessoa")
public class Endereco {

    @NonNull
    @Schema(description = "CEP do endereço", example = "12345-678")
    private String cep;

    @NonNull
    @Schema(description = "Rua ou logradouro", example = "Rua das Flores")
    private String rua;

    @NonNull
    @Schema(description = "Número do endereço", example = "123A")
    private String numero;

    @Schema(description = "Complemento do endereço", example = "Apto 45")
    private String complemento;

}
