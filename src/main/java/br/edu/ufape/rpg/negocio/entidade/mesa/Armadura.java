package br.edu.ufape.rpg.negocio.entidade.mesa;

import br.edu.ufape.rpg.negocio.entidade.excecao.ValidacaoException;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Armadura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; // Identificador único
    private String nome;
    private String tipo;

    public Armadura() {}

    public Armadura(String nome, String tipo) {
        if(nome == null || nome.isEmpty()) {
            throw new ValidacaoException("Nome não pode ser vazio.");
        }
        this.nome = nome;
        this.tipo = tipo;
    }
  
    public String getNome() {
        return nome;
    }
    public String getTipo() {
        return tipo;
    }
    
}