package br.csi.floricultura.service;

import br.csi.floricultura.model.produto.Produto;
import br.csi.floricultura.model.produto.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    // Salvar produto
    public Produto salvar(Produto produto) {
        return repository.save(produto);
    }

    // Atualizar produto
    public Produto atualizar(Produto produto) {
        Produto p = repository.findById(produto.getId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        p.setNome(produto.getNome());
        p.setDescricao(produto.getDescricao());
        p.setPreco(produto.getPreco());
        p.setEstoque(produto.getEstoque());
        return repository.save(p);
    }

    // Excluir produto
    public void excluir(Long id) {
        if (!repository.existsById(id)) throw new RuntimeException("Produto não encontrado");
        repository.deleteById(id);
    }

    // Listar todos
    public List<Produto> listar() {
        return repository.findAll();
    }

    // Buscar por ID
    public Produto getProduto(Long id) {
        return repository.findById(id).orElse(null);
    }

    // Listar por nome
    public List<Produto> listarPorNome(String nome) {
        return repository.findProdutoByNome(nome);
    }

}
