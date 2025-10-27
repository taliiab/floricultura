package br.csi.floricultura.model.entrega;

import br.csi.floricultura.model.pedido.Pedido;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "entrega")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @OneToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    private LocalDateTime dataEntrega;

    @NonNull
    private String status; //ex: "Aguardando", "Em Rota", "Entregue", "Cancelada"

    private String rastreio;
}
