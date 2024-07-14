package br.com.alura.ForumHub.api.models.Topico;

public record DadosDetalhesTopico(
        Long id,
        String titulo,
        String mensagem,
        String data_criacao,
        String estado_do_topico,
        Long autor_id,
        Long curso_id
) {
    public DadosDetalhesTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getData_criacao(),
                topico.getEstado_do_topico(),
                topico.getAutor_id(),
                topico.getCurso_id()
        );
    }
}
