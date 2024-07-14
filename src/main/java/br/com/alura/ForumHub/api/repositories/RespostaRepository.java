package br.com.alura.ForumHub.api.repositories;

import br.com.alura.ForumHub.api.models.Resposta.Resposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RespostaRepository extends JpaRepository<Resposta, Long> {
    @Query("select r from Resposta r where r.topico_id = :topicoId")
    List<Resposta> findAllByTopicoId(String topicoId);
}
