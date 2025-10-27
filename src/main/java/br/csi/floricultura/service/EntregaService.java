package br.csi.floricultura.service;

import br.csi.floricultura.model.entrega.Entrega;
import br.csi.floricultura.model.entrega.EntregaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntregaService {

    private final EntregaRepository repository;

    public EntregaService(EntregaRepository repository) {
        this.repository = repository;
    }

    public void salvar(Entrega entrega) {
        this.repository.save(entrega);
    }

    public List<Entrega> listar() {
        return this.repository.findAll();
    }

    public Entrega getEntrega(Long id) {
        return this.repository.findById(id).orElse(null);
    }

    public void excluir(Long id) {
        this.repository.deleteById(id);
    }

    public void atualizar(Entrega entrega) {
        Entrega e = this.repository.getReferenceById(entrega.getId());
        e.setPedido(entrega.getPedido());
        e.setDataEntrega(entrega.getDataEntrega());
        e.setStatus(entrega.getStatus());
        e.setRastreio(entrega.getRastreio());
        this.repository.save(e);
    }


}