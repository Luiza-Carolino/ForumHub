package br.com.alura.ForumHub.api.models.Topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroTopico(
        @NotBlank
        String titulo,
        @NotBlank
        String mensagem,
        @NotBlank
        // @Pattern(regexp = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$")
        String data_criacao,
        @NotBlank
        String estado_do_topico,
        @NotNull
        Long autor_id,
        @NotNull
        Long curso_id
) {
}
