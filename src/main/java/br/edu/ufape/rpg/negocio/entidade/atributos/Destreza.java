package br.edu.ufape.rpg.negocio.entidade.atributos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Destreza {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected long id;
  protected int valor;
  protected int modificador;
  protected int resistenciaDestreza;
  //pericias
  protected int acrobacia;
  protected int prestidigitacao;
  protected int furtividade;

  public Destreza() {}

  public Destreza(int valor) {
    this.valor = valor;
    this.modificador = (valor - 10) / 2;
    this.resistenciaDestreza = modificador;
    this.acrobacia = modificador;
    this.prestidigitacao = modificador;
    this.furtividade = modificador;
  }

  //Getters
  public int getValor() {
    return valor;
  }
  public int getModificador() {
    return modificador;
  }
  public int resistenciaDestreza() {
    return resistenciaDestreza;
  }

  //Setters
  public void setValor(int valor) {
    this.valor = valor;
  }
}