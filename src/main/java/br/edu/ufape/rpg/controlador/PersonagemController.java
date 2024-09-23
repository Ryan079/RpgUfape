package br.edu.ufape.rpg.controlador;

import br.edu.ufape.rpg.negocio.entidade.personagem.Personagem;
import br.edu.ufape.rpg.repositorio.PersonagemRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/personagem")
public class PersonagemController {

    private final PersonagemRepository personagemRepository;

    public PersonagemController(PersonagemRepository personagemRepository) {
        this.personagemRepository = personagemRepository;
    }

    @PostMapping("/adicionar")
    public ResponseEntity<String> adicionarPersonagem(@RequestBody Personagem personagem) {

        personagemRepository.save(personagem);
        return ResponseEntity.ok("Personagem adicionado com sucesso!");
    }
}
