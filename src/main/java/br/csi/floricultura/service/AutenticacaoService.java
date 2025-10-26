package br.csi.floricultura.service;

import br.csi.floricultura.model.pessoa.Pessoa;
import br.csi.floricultura.model.pessoa.PessoaRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AutenticacaoService implements UserDetailsService {

    private final PessoaRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Pessoa pessoa = repository.findPessoaByEmail(email);

        if (pessoa == null) {
            throw new UsernameNotFoundException("Usuario nao encontrado");
        }

        return pessoa;
    }
}
