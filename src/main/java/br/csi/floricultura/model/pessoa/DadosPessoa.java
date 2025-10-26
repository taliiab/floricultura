package br.csi.floricultura.model.pessoa;

public record DadosPessoa(Long id, String email, String permissao) {
    public DadosPessoa(Pessoa pessoa) {
        this(pessoa.getId(), pessoa.getEmail(), pessoa.getPermissao());
    }
}
