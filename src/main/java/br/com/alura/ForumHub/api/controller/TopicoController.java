package br.com.alura.ForumHub.api.controller;

import br.com.alura.ForumHub.api.exception.ValidacaoException;
import br.com.alura.ForumHub.api.models.Topico.DadosCadastroTopico;
import br.com.alura.ForumHub.api.models.Topico.DadosDetalhesTopico;
import br.com.alura.ForumHub.api.models.Topico.Topico;
import br.com.alura.ForumHub.api.repositories.CursoRepository;
import br.com.alura.ForumHub.api.repositories.TopicoRepository;
import br.com.alura.ForumHub.api.repositories.UsuarioRepository;
import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    public ResponseEntity criar(@RequestBody @Valid DadosCadastroTopico dadosTopico, UriComponentsBuilder uriBuilder) {

        Long cursoId = dadosTopico.curso_id();
        Long autorId = dadosTopico.autor_id();

        // Verifica se o curso existe
        if (!cursoRepository.existsById(cursoId)) {
            return ResponseEntity.badRequest().body(new ValidacaoException("Curso com ID '" + cursoId + "' não encontrado."));
        }

        // Verifica se o usuário existe
        if (!usuarioRepository.existsById(autorId)) {
            return ResponseEntity.badRequest().body(new ValidacaoException("Usuário com ID '" + autorId + "' não encontrado."));
        }

        var topico = new Topico(dadosTopico);
        topicoRepository.save(topico);

        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhesTopico(topico));
    }

    @GetMapping
    public ResponseEntity<List<DadosDetalhesTopico>> listar() {
        List<Topico> topicos = topicoRepository.findAll();

        List<DadosDetalhesTopico> detalhesTopicos = topicos.stream().map(topico -> new DadosDetalhesTopico(topico)).toList();

        return ResponseEntity.ok(detalhesTopicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhesTopico> detalhes(@PathVariable(name = "id") Long topicoId) {
        Topico topico = topicoRepository.getReferenceById(topicoId);

        return ResponseEntity.ok(new DadosDetalhesTopico(topico));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalhesTopico> atualizar(@PathVariable(name = "id") Long topicoId, @RequestBody DadosCadastroTopico dadosTopico) {
        Topico topico = topicoRepository.getReferenceById(topicoId);
        topico.atualizarDados(dadosTopico, topicoId, topicoRepository);

        return ResponseEntity.ok(new DadosDetalhesTopico(topico));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable(name = "id") Long topicoId) {
        if( topicoRepository.existsById(topicoId) ) {
            topicoRepository.deleteById(topicoId);

            return ResponseEntity.ok(
                    "Tópico com ID '" + topicoId + "' removido com sucesso."
            );
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                "Nenhum tópico encontrado com o ID '" + topicoId + "'."
        );
    }
}
