package br.com.alura.ForumHub.api.models.Usuario;

public record DadosDetalhesUsuario(
        Long id,
        String nome,
        String email
) {
    public DadosDetalhesUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }
}
