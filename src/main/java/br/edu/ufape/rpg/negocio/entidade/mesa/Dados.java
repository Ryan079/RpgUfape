package br.edu.ufape.rpg.negocio.entidade.mesa;

import br.edu.ufape.rpg.negocio.entidade.excecao.LimiteExcedidoException;

import java.util.Random;

//Classe utilitária para gerar números aleatórios
public class Dados {
    private static Random random;

    public Dados() {
        random = new Random();
    }

    public static int rolarDado(int lados) {
        if (lados < 1) {
            throw new LimiteExcedidoException("O dado deve ter pelo menos 1 lado.");
        }
        return random.nextInt(lados) + 1; 
    }

    public static int rolarD4() {
        return rolarDado(4);
    }

    public static int rolarD6() {
        return rolarDado(6);
    }

    public static int rolarD8() {
        return rolarDado(8);
    }

    public static int rolarD10() {
        return rolarDado(10);
    }

    public static int rolarD12() {
        return rolarDado(12);
    }

    public static int rolarD20() {
        return rolarDado(20);
    }

    public static int rolarD100() {
        return rolarDado(100);
    }
}