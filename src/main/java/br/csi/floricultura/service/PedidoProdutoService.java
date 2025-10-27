package br.csi.floricultura.service;

import br.csi.floricultura.model.pedido.PedidoProduto;
import br.csi.floricultura.model.pedido.PedidoProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoProdutoService {

    private final PedidoProdutoRepository repository;

    public PedidoProdutoService(PedidoProdutoRepository repository) {
        this.repository = repository;
    }

    public PedidoProduto salvar(PedidoProduto pedidoProduto) {
        return repository.save(pedidoProduto);
    }

    public PedidoProduto atualizar(PedidoProduto pedidoProduto) {
        if (pedidoProduto.getId() == null || !repository.existsById(pedidoProduto.getId())) {
            throw new RuntimeException("PedidoProduto não encontrado para atualização");
        }
        return repository.save(pedidoProduto);
    }

    public void excluir(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("PedidoProduto não encontrado para exclusão");
        }
        repository.deleteById(id);
    }

    public List<PedidoProduto> listar() {
        return repository.findAll();
    }

    public PedidoProduto getPedidoProduto(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<PedidoProduto> listarPorPedido(Long pedidoId) {
        return repository.findPedidoProdutoByPedidoId(pedidoId);
    }
}
