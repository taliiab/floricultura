package br.csi.floricultura.model.pedido;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoProdutoRepository extends JpaRepository<PedidoProduto, Long> {

    List<PedidoProduto> findPedidoProdutoByPedidoId(Long pedidoId);


}