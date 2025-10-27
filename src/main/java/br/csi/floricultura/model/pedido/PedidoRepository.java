package br.csi.floricultura.model.pedido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query("SELECT p FROM Pedido p LEFT JOIN FETCH p.produtos WHERE p.id = :id")
    Optional<Pedido> findByIdWithProdutos(@Param("id") Long id);

    @Query("SELECT DISTINCT p FROM Pedido p LEFT JOIN FETCH p.produtos")
    List<Pedido> findAllWithProdutos();
}
