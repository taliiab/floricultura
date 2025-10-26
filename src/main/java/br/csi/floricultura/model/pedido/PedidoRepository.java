package br.csi.floricultura.model.pedido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    // Busca um pedido pelo ID incluindo os produtos
    @Query("SELECT p FROM Pedido p LEFT JOIN FETCH p.produtos WHERE p.id = :id")
    Optional<Pedido> findByIdWithProdutos(@Param("id") Long id);

    // Lista todos os pedidos incluindo os produtos
    @Query("SELECT DISTINCT p FROM Pedido p LEFT JOIN FETCH p.produtos")
    List<Pedido> findAllWithProdutos();
}
