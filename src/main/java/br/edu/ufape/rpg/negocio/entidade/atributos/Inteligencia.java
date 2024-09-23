package br.edu.ufape.rpg.negocio.entidade.atributos;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Inteligencia {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected long id;
  protected int valor;
  protected int modificador;
  protected int resistenciaInteligencia;
  //pericias
  protected int arcanismo;
  protected int historia;
  protected int investigacao;
  protected int natureza;
  protected int religiao;

  public Inteligencia() {}

  public Inteligencia(int valor) {
    this.valor = valor;
    this.modificador = (valor - 10) / 2;
    this.resistenciaInteligencia = modificador;
    this.arcanismo = modificador;
    this.historia = modificador;
    this.investigacao = modificador;
    this.natureza = modificador;
    this.religiao = modificador;
  }

  //Getters
  public int getValor() {
    return valor;
  }
  public int getModificador() {
    return modificador;
  }
  public int getResistenciaInteligencia() {
    return resistenciaInteligencia;
  }

  //Setters
  public void setValor(int valor) {
    this.valor = valor;
  }
  
}