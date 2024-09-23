package br.edu.ufape.rpg.negocio.entidade.atributos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Sabedoria {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected long id;
  protected int valor;
  protected int modificador;
  protected int resistenciaSabedoria;
  //pericias
  protected int adestrarAnimais;
  protected int intuicao;
  protected int medicina;
  protected int sobrevivencia;

  public Sabedoria() {}

  public Sabedoria(int valor) {
    this.valor = valor;
    this.modificador = (valor - 10) / 2;
    this.resistenciaSabedoria = modificador;
    this.adestrarAnimais = modificador;
    this.intuicao = modificador;
    this.medicina = modificador;
    this.sobrevivencia = modificador;
  }

  //Getters
  public int getValor() {
    return valor;
  }
  public int getModificador() {
    return modificador;
  }
  public int getResistenciaSabedoria() {
    return resistenciaSabedoria;
  }

  //Setters
  public void setValor(int valor) {
    this.valor = valor;
  }
}