package br.csi.floricultura.model.entrega;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EntregaRepository extends JpaRepository<Entrega, Long> {

    // Busca entrega por status
    Entrega findEntregaByStatus(String status);

    // Busca entrega pelo código de rastreio
    Entrega findEntregaByRastreio(String rastreio);


}