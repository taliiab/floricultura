package br.csi.floricultura.model.pedido;

import br.csi.floricultura.model.pessoa.Pessoa;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pedido")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @ManyToOne(fetch = FetchType.EAGER) // garante que o usuário será carregado junto
    @JoinColumn(name = "usuario_id")
    private Pessoa pessoa;

    @NonNull
    private LocalDateTime dataPedido;

    @NonNull
    private BigDecimal valorTotal;

    @NonNull
    private String status; // Ex: "Pendente", "Processando", "Enviado", "Entregue", "Cancelado"

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<PedidoProduto> produtos;
}
