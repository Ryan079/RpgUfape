package br.edu.ufape.rpg.controlador;
// Controlador com grupos

import br.edu.ufape.rpg.negocio.entidade.personagem.Personagem;
import br.edu.ufape.rpg.repositorio.PersonagemRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/jogo")
public class JogoController {

    private final PersonagemRepository personagemRepository;
    private List<Personagem> grupo1; // Armazena os personagens do Grupo 1
    private List<Personagem> grupo2; // Armazena os personagens do Grupo 2

    public JogoController(PersonagemRepository personagemRepository) {
        this.personagemRepository = personagemRepository;
        this.grupo1 = new ArrayList<>();
        this.grupo2 = new ArrayList<>();
    }

    // Inicializa a batalha com personagens específicos em cada grupo
    @PostMapping("/iniciarBatalha")
    public ResponseEntity<String> iniciarBatalha(@RequestBody Map<String, List<Long>> grupos) {
        List<Long> grupo1Ids = grupos.get("grupo1");
        List<Long> grupo2Ids = grupos.get("grupo2");

        // Verifica se os grupos possuem IDs de personagens
        if (grupo1Ids == null || grupo2Ids == null || grupo1Ids.isEmpty() || grupo2Ids.isEmpty()) {
            return ResponseEntity.badRequest().body("Os grupos não podem estar vazios!");
        }

        // Buscar os personagens nos grupos pelo ID
        grupo1 = personagemRepository.findAllById(grupo1Ids);
        grupo2 = personagemRepository.findAllById(grupo2Ids);

        if (grupo1.isEmpty() || grupo2.isEmpty()) {
            return ResponseEntity.badRequest().body("Um ou ambos os grupos possuem personagens inválidos!");
        }

        // A batalha está pronta para começar
        return ResponseEntity.ok("Batalha iniciada entre os grupos!");
    }

    // Permite adicionar personagens a um grupo específico
    @PostMapping("/adicionarPersonagem")
    public ResponseEntity<String> adicionarPersonagem(@RequestParam String grupo, @RequestBody List<Long> personagemIds) {
        if (personagemIds == null || personagemIds.isEmpty()) {
            return ResponseEntity.badRequest().body("A lista de IDs de personagens não pode estar vazia!");
        }

        List<Personagem> personagensAdicionados = personagemRepository.findAllById(personagemIds);

        if (personagensAdicionados.isEmpty()) {
            return ResponseEntity.badRequest().body("Nenhum personagem válido foi encontrado!");
        }

        if ("grupo1".equalsIgnoreCase(grupo)) {
            grupo1.addAll(personagensAdicionados);
        } else if ("grupo2".equalsIgnoreCase(grupo)) {
            grupo2.addAll(personagensAdicionados);
        } else {
            return ResponseEntity.badRequest().body("Grupo inválido! Use 'grupo1' ou 'grupo2'.");
        }

        return ResponseEntity.ok("Personagens adicionados ao " + grupo + "!");
    }

    // Permite que o jogador ataque
    @PostMapping("/atacar")
    public ResponseEntity<String> atacar(@RequestParam Long atacanteId, @RequestParam Long alvoId) {
        Optional<Personagem> atacanteOpt = personagemRepository.findById(atacanteId);
        Optional<Personagem> alvoOpt = personagemRepository.findById(alvoId);

        if (atacanteOpt.isEmpty() || alvoOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Atacante ou alvo inválido!");
        }

        Personagem atacante = atacanteOpt.get();
        Personagem alvo = alvoOpt.get();

        // Realizar o ataque
        atacante.atacar(alvo);

        // Atualizar o alvo no banco de dados após o ataque
        personagemRepository.save(alvo);

        // Verificar se a batalha terminou
        String resultadoBatalha = verificarFimDaBatalha();
        if (resultadoBatalha != null) {
            return ResponseEntity.ok(resultadoBatalha);
        }

        return ResponseEntity.ok(atacante.getNome() + " atacou " + alvo.getNome() + "!");
    }

    // Verifica o status da batalha
    private String verificarFimDaBatalha() {
        boolean grupo1Derrotado = grupo1.stream().allMatch(p -> p.getPontosDeVida() <= 0);
        boolean grupo2Derrotado = grupo2.stream().allMatch(p -> p.getPontosDeVida() <= 0);

        if (grupo1Derrotado) {
            return "Batalha terminada! Grupo 2 venceu!";
        } else if (grupo2Derrotado) {
            return "Batalha terminada! Grupo 1 venceu!";
        }

        return null;  // Se nenhum grupo foi completamente derrotado, a batalha continua
    }

    // Exibe o status atual dos grupos
    @GetMapping("/statusGrupos")
    public ResponseEntity<Map<String, List<Personagem>>> statusGrupos() {
        Map<String, List<Personagem>> statusGrupos = new HashMap<>();
        statusGrupos.put("Grupo 1", grupo1);
        statusGrupos.put("Grupo 2", grupo2);

        return ResponseEntity.ok(statusGrupos);
    }
}
