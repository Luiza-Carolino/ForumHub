package br.com.alura.ForumHub.api.models.Curso;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroCurso(
    @NotBlank
    String nome,
    @NotBlank
    String categoria
) {
}
