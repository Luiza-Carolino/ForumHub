package br.com.alura.ForumHub.api.models.Curso;

public record DadosDetalhesCurso(
        Long id,
        String nome,
        String categoria
) {
    public DadosDetalhesCurso(Curso curso) {
        this(
                curso.getId(),
                curso.getNome(),
                curso.getCategoria()
        );
    }
}
