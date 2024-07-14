package br.com.alura.ForumHub.api.repositories;

import br.com.alura.ForumHub.api.models.Curso.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

}