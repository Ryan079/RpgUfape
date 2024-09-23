package br.edu.ufape.rpg.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import br.edu.ufape.rpg.negocio.entidade.personagem.Personagem;

@Repository
public interface PersonagemRepository extends JpaRepository<Personagem, Long> {

    List<Personagem> findByNome(String nome);
}
