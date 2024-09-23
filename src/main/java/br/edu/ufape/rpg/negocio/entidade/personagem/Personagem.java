package br.edu.ufape.rpg.negocio.entidade.personagem;

import br.edu.ufape.rpg.negocio.entidade.atributos.Atributos;
import br.edu.ufape.rpg.negocio.entidade.excecao.AcaoInvalidaException;
import br.edu.ufape.rpg.negocio.entidade.excecao.ValidacaoException;
import br.edu.ufape.rpg.negocio.entidade.mesa.Arma;
import br.edu.ufape.rpg.negocio.entidade.mesa.Armadura;
import br.edu.ufape.rpg.negocio.entidade.mesa.Dados;
import jakarta.persistence.*;

@Entity
@Table(name = "personagens")
public class Personagem extends PersonagemAbstrato {
    private int dados;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private enum Atributo {
        FORCA,
        DESTREZA,
        CONSTITUICAO,
        INTELIGENCIA,
        SABEDORIA,
        CARISMA
    }

    public Personagem() {
        super();
    }

    public Personagem(String nome, String raca, String classePersonagem, int nivel, int pontosDeVidaMaximo, Atributos atributos, Arma arma, Armadura armadura) {
      super(nome, raca, classePersonagem, nivel, pontosDeVidaMaximo, atributos, arma, armadura);
      this.classeArmadura = getClasseArmadura();
        if (nome == null || nome.isEmpty()) {
            throw new ValidacaoException("O nome do personagem não pode ser vazio.");
        }
        if (raca == null || raca.isEmpty()) {
            throw new ValidacaoException("A raça do personagem não pode ser vazia.");
        }
        if (classePersonagem == null || classePersonagem.isEmpty()) {
            throw new ValidacaoException("A classe do personagem não pode ser vazia.");
        }
        if (nivel < 1) {
            throw new ValidacaoException("O nível do personagem deve ser pelo menos 1.");
        }
        if (pontosDeVidaMaximo < 0) {
            throw new ValidacaoException("Os pontos de vida máximos não podem ser negativos.");
        }
    }

    //Getters
    public String getNome() {
        return nome;
    }
    public String getRaca() {
        return raca;
    }
    public int getNivel() {
        return nivel;
    }
    public int getPontosDeVida() {
        return pontosDeVida;
    }
    public int getPontosDeVidaMaximo() {
        return pontosDeVidaMaximo;
    }
    public Atributos getAtributos() {
        return atributos;
    }
    public Arma getArma() {
        return arma;
    }
    public int getClasseArmadura() {
        int CA = 10 + this.atributos.getDesMod();
        if (armadura.getTipo().equals("Leve")) {
            CA = 11 + this.atributos.getDesMod();
        }
        if (armadura.getTipo().equals("Media")) {
            if (this.atributos.getDesMod() > 2) { 
                CA = 14 + 2;
            }
            else {
                CA = 14 + this.atributos.getDesMod();
            }
        }
        if (armadura.getTipo().equals("Pesada")) {
            CA = 17;
        }
        return CA;
    }
    public String getClassePersonagem() {
        return classePersonagem;
    }
    //Setters
    public void setArma(Arma arma) {
        this.arma = arma;
    }

    public void setArmadura(Armadura armadura) {
        this.armadura = armadura;
    }

    public void setPontosDeVida(int pontosDeVida) {
        this.pontosDeVida = pontosDeVida;
    }

    //Metodos
    public int Iniciativa() {
        return this.atributos.getDesMod() + Dados.rolarD20();
    }
    @Override
    public void subirNivel() {
        int XPproximoNivel = (this.nivel + 1) * 200;
        if (XP >= XPproximoNivel) {
          this.nivel++;
        } else {
            throw new AcaoInvalidaException("XP insuficiente para subir de nível");
        }
    }
    public void atualizarAtributos(Atributo atributo) {
        switch (atributo) {
          case FORCA:
              this.atributos.setForca(this.atributos.getForca() + 2);
              break;
          case DESTREZA:
              this.atributos.setDestreza(this.atributos.getDestreza() + 2);
              break;
          case CONSTITUICAO:
            this.atributos.setConstituicao(this.atributos.getConstituicao() + 2);
              break;
          case INTELIGENCIA:
              this.atributos.setInteligencia(this.atributos.getInteligencia() + 2);
              break;
          case CARISMA:
              this.atributos.setCarisma(this.atributos.getCarisma() + 2);
              break;
          case SABEDORIA:
              this.atributos.setSabedoria(this.atributos.getSabedoria() + 2);
              break;
        }
    }
    @Override
    public void atacar(Personagem alvo){
        int bonusArma = 0;
        if (arma.getTipo().equals("CorpoACorpo")) {
          bonusArma = this.atributos.getForMod();
        }
        if (arma.getTipo().equals("Distancia")) {
          bonusArma = this.atributos.getDesMod();
        }
        if (arma.getTipo().equals("Magico")) {
          bonusArma = this.atributos.getIntMod();
        }
        int rolarAcerto = Dados.rolarD20() + bonusArma;
        if (alvo.getClasseArmadura() <= rolarAcerto){
            int dano = this.arma.getDano() + bonusArma;
            if(alvo.getPontosDeVida() - dano < 0) {
                throw new ValidacaoException("O ataque causaria dano negativo");
            }
           alvo.setPontosDeVida(alvo.getPontosDeVida() - dano);
        }
    }
}