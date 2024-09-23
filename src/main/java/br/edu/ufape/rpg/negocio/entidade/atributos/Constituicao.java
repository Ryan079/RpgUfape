package br.edu.ufape.rpg.negocio.entidade.atributos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Constituicao {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected long id;
  protected int valor;
  protected int modificador;
  protected int resistenciaConstituicao;

  public Constituicao() {}

  public Constituicao(int valor) {
    this.valor = valor;
    this.modificador = (valor - 10) / 2;
    this.resistenciaConstituicao = modificador;
  }

  //Getters
  public int getValor() {
    return valor;
  }
  public int getModificador() {
    return modificador;
  }
  public int getResistenciaConstituicao() {
    return resistenciaConstituicao;
  }

  //Setters
  public void setValor(int valor) {
    this.valor = valor;
  }
}