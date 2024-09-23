package br.edu.ufape.rpg.negocio.entidade.atributos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Carisma {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected long id;
  protected int valor;
  protected int modificador;
  protected int resistenciaCarisma;
  //pericias
  protected int enganacao;
  protected int intimidacao;
  protected int atuacao;
  protected int persuasao;

  public Carisma() {}
  
  public Carisma(int valor){
    this.valor = valor;
    this.modificador = (valor - 10) / 2;
    this.resistenciaCarisma = modificador;
    this.enganacao = modificador;
    this.intimidacao = modificador;
    this.atuacao = modificador;
    this.persuasao = modificador;
  }

  //Getters
  public int getValor() {
    return valor;
  }
  public int getModificador() {
    return modificador;
  }
  public int getResistenciaCarisma() {
    return resistenciaCarisma;
  }

  //Setters
  public void setValor(int valor) {
    this.valor = valor;
  }
}
