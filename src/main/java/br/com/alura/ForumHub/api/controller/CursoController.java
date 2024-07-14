package br.com.alura.ForumHub.api.controller;

import br.com.alura.ForumHub.api.models.Curso.Curso;
import br.com.alura.ForumHub.api.models.Curso.DadosCadastroCurso;
import br.com.alura.ForumHub.api.models.Curso.DadosDetalhesCurso;
import br.com.alura.ForumHub.api.repositories.CursoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhesCurso> criar(@RequestBody @Valid DadosCadastroCurso dadosCurso, UriComponentsBuilder uriBuilder) {
        var curso = new Curso(dadosCurso);
        repository.save(curso);

        var uri = uriBuilder.path("/cursos/{id}").buildAndExpand(curso.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhesCurso(curso));
    }

    @GetMapping
    public ResponseEntity<List<DadosDetalhesCurso>> listar() {
        List<Curso> cursos = repository.findAll();

        List<DadosDetalhesCurso> detalhesTopicos = cursos.stream().map(curso -> new DadosDetalhesCurso(curso)).toList();

        return ResponseEntity.ok(detalhesTopicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhesCurso> detalhes(@PathVariable(name = "id") Long cursoId) {
        Curso curso = repository.getReferenceById(cursoId);

        return ResponseEntity.ok(new DadosDetalhesCurso(curso));
    }
}
