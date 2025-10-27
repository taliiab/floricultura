package br.csi.floricultura.model.entrega;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EntregaRepository extends JpaRepository<Entrega, Long> {

    Entrega findEntregaByStatus(String status);

    Entrega findEntregaByRastreio(String rastreio);


}