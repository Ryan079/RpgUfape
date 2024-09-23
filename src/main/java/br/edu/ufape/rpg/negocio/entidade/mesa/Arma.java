package br.edu.ufape.rpg.negocio.entidade.mesa;

import br.edu.ufape.rpg.negocio.entidade.excecao.ValidacaoException;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Arma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String tipo;
    private int dano;
    private int dados;

    public Arma() {}

    public Arma(String nome, String tipo, int dano,int dados) {
        if(nome == null || nome.isEmpty()){
            throw new ValidacaoException("Nome não pode ser nulo.");
        }
        if(dano < 0) {
            throw new ValidacaoException("Dano não pode ser negativo");
        }
        this.nome = nome;
        this.tipo = tipo;
        this.dados = dados;
        this.dano = dano;
    }

    private void calcularDano(int ladosDado) {
        dados = Dados.rolarDado(ladosDado);
    }

    public void exibirInfo() {
        System.out.println("Nome da Arma: " + nome);
        System.out.println("Tipo: " + tipo);
        System.out.println("Dano: " + dano);
    }
    
    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public int getDano() {
        return this.dano;
    }

}
