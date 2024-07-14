package br.com.alura.ForumHub.api.models.Resposta;

import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

@Getter
@Setter
@Table(name = "resposta")
@Entity(name = "Resposta")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Resposta {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensagem;
    // @PastOrPresent
    private String data_criacao;
    private String solucao;
    private Long topico_id;
    private Long autor_id;

    public Resposta(DadosCadastroResposta resposta) {
        this.mensagem = resposta.mensagem();
        this.data_criacao = resposta.data_criacao();
        this.solucao = resposta.solucao();
        this.topico_id = resposta.topico_id();
        this.autor_id = resposta.autor_id();
    }
}
