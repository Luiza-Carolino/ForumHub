package br.com.alura.ForumHub.api.repositories;

import br.com.alura.ForumHub.api.models.Topico.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {
}
