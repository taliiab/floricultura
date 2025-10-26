package br.csi.floricultura.service;

import br.csi.floricultura.model.pagamento.Pagamento;
import br.csi.floricultura.model.pagamento.PagamentoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PagamentoService {

    private final PagamentoRepository repository;

    public PagamentoService(PagamentoRepository repository) {
        this.repository = repository;
    }

    public Pagamento salvar(Pagamento pagamento) {
        return repository.save(pagamento);
    }

    public List<Pagamento> listar() {
        return repository.findAll();
    }

    public Pagamento getPagamento(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }

    public Pagamento atualizar(Pagamento pagamento) {
        Pagamento p = repository.getReferenceById(pagamento.getId());
        p.setPedido(pagamento.getPedido());
        p.setStatus(pagamento.getStatus());
        return repository.save(p);
    }

    public List<Pagamento> listarPorStatus(String status) {
        return repository.findAllByStatus(status);
    }
}
