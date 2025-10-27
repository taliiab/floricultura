package br.csi.floricultura.service;

import br.csi.floricultura.model.pessoa.Pessoa;
import br.csi.floricultura.model.pessoa.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PessoaService {
    private final PessoaRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PessoaService(PessoaRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public Pessoa salvar(Pessoa pessoa) {
        pessoa.setSenha(passwordEncoder.encode(pessoa.getSenha())); //criptografa a senha antes de salvar
        return repository.save(pessoa);
    }

    public List<Pessoa> listar() {
        return this.repository.findAll();
    }

    public Pessoa getPessoa(Long id) {
        return this.repository.findById(id).orElse(null);
    }

    public void excluir(Long id){
        this.repository.deleteById(id);
    }

    public void atualizar(Pessoa pessoa){
        Pessoa p = this.repository.getReferenceById(pessoa.getId());
        p.setNome(pessoa.getNome());
        p.setCpf(pessoa.getCpf());
        p.setEmail(pessoa.getEmail());
        p.setSenha(pessoa.getSenha());
        p.setEndereco(pessoa.getEndereco());
        this.repository.save(p);
    }

    public void atualizarUUID(Pessoa pessoa){
        Pessoa p = this.repository.findPessoaByUuid(pessoa.getUuid());
        p.setNome(pessoa.getNome());
        p.setCpf(pessoa.getCpf());
        p.setEmail(pessoa.getEmail());
        p.setSenha(pessoa.getSenha());
        p.setEndereco(pessoa.getEndereco());
        this.repository.save(p);
    }


    public Pessoa getPessoaUUID(String uuid){
        UUID uuidFormatado = UUID.fromString(uuid);
        return this.repository.findPessoaByUuid(uuidFormatado);
    }

    public void deletarUUID(String uuid){
        this.repository.deletePessoaByUuid(UUID.fromString(uuid));
    }

    public Pessoa getPessoaPorCpf(String cpf){
        return this.repository.findPessoaByCpf(cpf);
    }

    public Pessoa getPessoaPorEmail(String email){
        return this.repository.findPessoaByEmail(email);
    }
}
