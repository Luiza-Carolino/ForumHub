package br.com.alura.ForumHub.api.controller;

import br.com.alura.ForumHub.api.exception.ValidacaoException;
import br.com.alura.ForumHub.api.models.Resposta.DadosCadastroResposta;
import br.com.alura.ForumHub.api.models.Resposta.DadosDetalhesResposta;
import br.com.alura.ForumHub.api.models.Resposta.Resposta;
import br.com.alura.ForumHub.api.repositories.RespostaRepository;
import br.com.alura.ForumHub.api.repositories.TopicoRepository;
import br.com.alura.ForumHub.api.repositories.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/topicos/{topicoId}/respostas")
public class RespostaController {

    @Autowired
    private RespostaRepository respostaRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    ResponseEntity criar(@PathVariable(name = "topicoId") Long topicoId, @RequestBody @Valid DadosCadastroResposta dadosResposta, UriComponentsBuilder uriBuilder) {

        Long autorId = dadosResposta.autor_id();

        // Verifica se o tópico existe
        if (!topicoRepository.existsById(topicoId)) {
            return ResponseEntity.badRequest().body(new ValidacaoException("Tópico com ID '" + topicoId + "' não encontrado."));
        }

        // Verifica se o usuário existe
        if (!usuarioRepository.existsById(autorId)) {
            return ResponseEntity.badRequest().body(new ValidacaoException("Usuário com ID '" + autorId + "' não encontrado."));
        }

        var resposta = new Resposta(dadosResposta);
        resposta.setTopico_id(topicoId);
        respostaRepository.save(resposta);

        var uri = uriBuilder.path("/respostas/{id}").buildAndExpand(resposta.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhesResposta(resposta));
    }

    @GetMapping
    ResponseEntity<List<DadosDetalhesResposta>> listar(@PathVariable(name = "topicoId") String topicoId) {
        List<Resposta> respostas = respostaRepository.findAllByTopicoId(topicoId);

        List<DadosDetalhesResposta> detalhesRespostas = respostas.stream().map(resposta -> new DadosDetalhesResposta(resposta)).toList();

        return ResponseEntity.ok(detalhesRespostas);
    }

    @GetMapping("/{respostaId}")
    public ResponseEntity<DadosDetalhesResposta> detalhes(@PathVariable(name = "respostaId") Long respostaId) {
        Resposta resposta = respostaRepository.getReferenceById(respostaId);

        return ResponseEntity.ok(new DadosDetalhesResposta(resposta));
    }
}
