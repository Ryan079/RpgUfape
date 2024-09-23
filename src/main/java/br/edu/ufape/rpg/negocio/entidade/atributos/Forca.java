package br.edu.ufape.rpg.negocio.entidade.atributos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.boot.internal.GenerationStrategyInterpreter;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Entity
public class Forca {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected long id;
  protected int valor;
  protected int modificador;
  protected int resistenciaForca;
  //pericias
  protected int atletismo;

  public Forca() {}

  public Forca(int valor) {
    this.valor = valor;
    this.modificador = (valor - 10) / 2;
    this.resistenciaForca = modificador;
    this.atletismo = modificador;
  }

  //Getters
  public int getValor() {
    return valor;
  }
  public int getModificador() {
    return modificador;
  }
  public int resistenciaForca() {
    return resistenciaForca;
  }

  //Setters
  public void setValor(int valor) {
    this.valor = valor;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }
}