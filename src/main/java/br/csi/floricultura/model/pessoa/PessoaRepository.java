package br.csi.floricultura.model.pessoa;

import br.csi.floricultura.model.pessoa.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    Pessoa findPessoaByUuid(UUID uuid);

    void deletePessoaByUuid(UUID uuid);

    Pessoa findPessoaByCpf(String cpf);

    Pessoa findPessoaByEmail(String email);
}
