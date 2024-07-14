package br.com.alura.ForumHub.api.models.Resposta;

public record DadosDetalhesResposta(
        Long id,
        String mensagem,
        String data_criacao,
        String solucao,
        Long topico_id,
        Long autor_id
) {
    public DadosDetalhesResposta(Resposta topico) {
        this(
                topico.getId(),
                topico.getMensagem(),
                topico.getData_criacao(),
                topico.getSolucao(),
                topico.getTopico_id(),
                topico.getAutor_id()
        );
    }
}
