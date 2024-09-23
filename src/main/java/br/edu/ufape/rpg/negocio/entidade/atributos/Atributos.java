package br.edu.ufape.rpg.negocio.entidade.atributos;

import jakarta.persistence.*;

@Entity
public class Atributos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "forca_id")
    private Forca forca = new Forca();
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "destreza_id")
    private Destreza destreza = new Destreza();
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "constituicao_id")
    private Constituicao constituicao = new Constituicao();
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "inteligencia_id")
    private Inteligencia inteligencia = new Inteligencia();
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sabedoria_id")
    private Sabedoria sabedoria = new Sabedoria();
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "carisma_id")
    private Carisma carisma = new Carisma();

    public Atributos(int inteligencia, int carisma, int forca, int destreza, int sabedoria, int constituicao) {
        this.inteligencia = new Inteligencia(inteligencia);
        this.carisma = new Carisma(carisma);
        this.forca = new Forca(forca);
        this.destreza = new Destreza(destreza);
        this.sabedoria = new Sabedoria(sabedoria);
        this.constituicao = new Constituicao(constituicao);
    }

    //Construtor Vazio
    public Atributos() {}

    //Inteligência
    public int getInteligencia() {
        return inteligencia.getValor();
    }

    public int getIntMod() {
        return inteligencia.getModificador();
    }

    public void setInteligencia(int inteligencia) {
        this.inteligencia.setValor(inteligencia);
    }
    
    //Carisma
    public int getCarisma() {
        return carisma.getValor();
    }

    public int getCarMod() {
        return carisma.getModificador();
    }

    public void setCarisma(int carisma) {
        this.carisma.setValor(carisma);
    }
    
    //Força
    public int getForca() {
        return forca.getValor();
    }

    public int getForMod() {
        return forca.getModificador();
    }

    public void setForca(int forca) {
        this.forca.setValor(forca);
    }

    //Destreza
    public int getDestreza() {
        return destreza.getValor();
    }

    public int getDesMod() {
        return destreza.getModificador();
    }

    public void setDestreza(int destreza) {
        this.destreza.setValor(destreza);
    }

    //Sabedoria
    public int getSabedoria() {
        return sabedoria.getValor();
    }

    public int getSabMod() {
        return sabedoria.getModificador();
    }

    public void setSabedoria(int sabedoria) {
        this.sabedoria.setValor(sabedoria);
    }

    //Constituição
    public int getConstituicao() {
        return constituicao.getValor();
    }

    public int GetConsMod() {
        return constituicao.getModificador();
    }

    public void setConstituicao(int constituicao) {
        this.constituicao.setValor(constituicao);
    }

}
