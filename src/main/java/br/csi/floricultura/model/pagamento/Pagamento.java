package br.csi.floricultura.model.pagamento;

import br.csi.floricultura.model.pedido.Pedido;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "pagamento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @OneToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @NonNull
    private String status; //ex: "Pendente", "Pago", "Cancelado"

}
