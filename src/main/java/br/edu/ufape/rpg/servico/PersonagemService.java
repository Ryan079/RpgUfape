package br.edu.ufape.rpg.servico;

import br.edu.ufape.rpg.repositorio.PersonagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import br.edu.ufape.rpg.negocio.entidade.personagem.Personagem;

@Service
public class PersonagemService {

    @Autowired
    private PersonagemRepository personagemRepository;

    public List<Personagem> listarTodosPersonagens() {
        return personagemRepository.findAll();
    }

    public Personagem salvarPersonagem(Personagem personagem) {
        return personagemRepository.save(personagem);
    }

    public Personagem buscarPersonagemPorId(Long id) {
        return personagemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Personagem não encontrado"));
    }

    public void deletarPersonagem(Long id) {
        personagemRepository.deleteById(id);
    }
}