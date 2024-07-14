package br.com.alura.ForumHub.api.models.Topico;

import br.com.alura.ForumHub.api.models.Usuario.Usuario;
import br.com.alura.ForumHub.api.repositories.TopicoRepository;
import br.com.alura.ForumHub.api.repositories.UsuarioRepository;
import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Table(name = "topico")
@Entity(name = "Topico")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensagem;
    // @PastOrPresent
    private String data_criacao;
    private String estado_do_topico;
    private Long autor_id;
    private Long curso_id;

    public Topico(DadosCadastroTopico topico) {
        this.titulo = topico.titulo();
        this.mensagem = topico.mensagem();
        this.data_criacao = topico.data_criacao();
        this.estado_do_topico = topico.estado_do_topico();
        this.autor_id = topico.autor_id();
        this.curso_id = topico.curso_id();
    }

    public void atualizarDados(DadosCadastroTopico dados, Long topicoid, TopicoRepository repository) {

        Topico dadosAtuais = repository.getReferenceById(topicoid);

        if(dados.titulo() != null) {
            this.titulo = dados.titulo();
        }
        else { this.titulo = dadosAtuais.getTitulo(); }

        if(dados.mensagem() != null) {
            this.mensagem = dados.mensagem();
        }
        else { this.mensagem = dadosAtuais.getMensagem(); }

        if(dados.data_criacao() != null) {
            this.data_criacao = dados.data_criacao();
        }
        else { this.data_criacao = dadosAtuais.getData_criacao(); }

        if(dados.estado_do_topico() != null) {
            this.estado_do_topico = dados.estado_do_topico();
        }
        else { this.estado_do_topico = dadosAtuais.getEstado_do_topico(); }

        if(dados.autor_id() != null) {
            this.autor_id = dados.autor_id();
        }
        else { this.autor_id = dadosAtuais.getAutor_id(); }

        if(dados.curso_id() != null) {
            this.curso_id = dados.curso_id();
        }
        else { this.curso_id = dadosAtuais.getCurso_id(); }
    }
}
