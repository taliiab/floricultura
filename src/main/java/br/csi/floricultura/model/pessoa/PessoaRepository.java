package br.csi.floricultura.model.pessoa;

import br.csi.floricultura.model.pessoa.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    Pessoa findPessoaByUuid(UUID uuid); //busca pessoa por UUID
    void deletePessoaByUuid(UUID uuid); //deleta pessoa por UUID

    Pessoa findPessoaByCpf(String cpf); //busca pessoa por cpf

    Pessoa findPessoaByEmail(String email); //busca pessoa por email
}
