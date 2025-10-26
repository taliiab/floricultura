package br.csi.floricultura.service;

import br.csi.floricultura.model.pedido.Pedido;
import br.csi.floricultura.model.pedido.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository repository;

    public PedidoService(PedidoRepository repository) {
        this.repository = repository;
    }

    public void salvar(Pedido pedido) {
        repository.save(pedido);
    }

    public void atualizar(Pedido pedido) {
        repository.save(pedido);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }

    // Lista todos os pedidos incluindo os produtos
    public List<Pedido> listar() {
        return repository.findAllWithProdutos();
    }

    // Busca um pedido por ID incluindo os produtos
    public Pedido getPedido(Long id) {
        return repository.findByIdWithProdutos(id).orElse(null);
    }
}
