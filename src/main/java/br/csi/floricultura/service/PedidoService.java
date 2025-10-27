package br.csi.floricultura.service;

import br.csi.floricultura.model.pedido.Pedido;
import br.csi.floricultura.model.pedido.PedidoRepository;
import br.csi.floricultura.model.pessoa.Pessoa;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository repository;

    public PedidoService(PedidoRepository repository) {
        this.repository = repository;
    }

    // Salvar um novo pedido, associando um usuário
    @Transactional
    public void salvar(Pedido pedido, Pessoa pessoa) {
        if (pedido.getValorTotal() == null) {
            throw new IllegalArgumentException("valorTotal não pode ser nulo");
        }
        if (pessoa == null) {
            throw new IllegalArgumentException("Usuário não pode ser nulo");
        }

        pedido.setPessoa(pessoa); // garante que usuario_id não será nulo
        repository.save(pedido);
    }

    // Atualizar um pedido existente
    @Transactional
    public void atualizar(Pedido pedido) {
        Pedido pedidoExistente = repository.findByIdWithProdutos(pedido.getId())
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        if (pedido.getValorTotal() == null) {
            throw new IllegalArgumentException("valorTotal não pode ser nulo");
        }

        // Atualiza campos simples
        pedidoExistente.setStatus(pedido.getStatus());
        pedidoExistente.setValorTotal(pedido.getValorTotal());
        pedidoExistente.setDataPedido(pedido.getDataPedido());

        // Atualiza produtos corretamente
        pedidoExistente.getProdutos().clear();
        if (pedido.getProdutos() != null) {
            pedidoExistente.getProdutos().addAll(pedido.getProdutos());
        }
        // Não precisa chamar save explicitamente, entidade já gerenciada
    }

    // Excluir um pedido
    @Transactional
    public void excluir(Long id) {
        repository.deleteById(id);
    }

    // Listar todos os pedidos com produtos
    @Transactional(readOnly = true)
    public List<Pedido> listar() {
        return repository.findAllWithProdutos();
    }

    // Buscar um pedido por ID com produtos
    @Transactional(readOnly = true)
    public Pedido getPedido(Long id) {
        return repository.findByIdWithProdutos(id).orElse(null);
    }
}
