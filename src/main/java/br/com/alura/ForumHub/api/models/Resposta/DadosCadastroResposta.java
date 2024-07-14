package br.com.alura.ForumHub.api.models.Resposta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroResposta(
        @NotBlank
        String mensagem,
        @NotBlank
        String data_criacao,
        @NotBlank
        String solucao,
        Long topico_id,
        @NotNull
        Long autor_id
) {
}
