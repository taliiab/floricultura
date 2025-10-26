package br.csi.floricultura.model.pedido;

import br.csi.floricultura.model.produto.Produto;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "pedido_produto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class PedidoProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @NonNull
    private Integer quantidade;

    @NonNull
    private BigDecimal precoUnitario;
}
