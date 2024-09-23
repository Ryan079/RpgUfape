package br.edu.ufape.rpg.negocio.entidade.personagem;

import br.edu.ufape.rpg.negocio.entidade.atributos.Atributos;
import br.edu.ufape.rpg.negocio.entidade.mesa.Arma;
import br.edu.ufape.rpg.negocio.entidade.mesa.Armadura;
import jakarta.persistence.*;


@MappedSuperclass
public abstract class PersonagemAbstrato {

    @Column(name = "nome")
        protected String nome;

        @Column(name = "raca")
        protected String raca;

        @Column(name = "classe_personagem")
        protected String classePersonagem;

        @Column(name = "nivel")
        protected int nivel;

        @Column(name = "xp")
        protected int XP;

        @Column(name = "classe_armadura")
        protected int classeArmadura;

        @Column(name = "pontos_de_vida")
        protected int pontosDeVida;

        @Column(name = "pontos_de_vida_maximo")
        protected int pontosDeVidaMaximo;

        @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JoinColumn(name = "atributos_id")
        protected Atributos atributos;

        @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JoinColumn(name = "arma_id")
        protected Arma arma;

        @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JoinColumn(name = "armadura_id")
        protected Armadura armadura;

    public PersonagemAbstrato() {}

    public PersonagemAbstrato(String nome, String raca, String classePersonagem, int nivel, int pontosDeVidaMaximo, Atributos atributos, Arma arma, Armadura armadura) {
        this.nome = nome;
        this.raca = raca;
        this.classePersonagem = classePersonagem;
        this.nivel = nivel;
        this.XP = 0;
        this.pontosDeVida = pontosDeVidaMaximo;
        this.pontosDeVidaMaximo = pontosDeVidaMaximo;
        this.atributos = atributos;
        this.arma = arma;
        this.armadura = armadura;
    }

    public abstract void atacar(Personagem alvo);
    public abstract void subirNivel();
}
