package br.edu.ufape.rpg.negocio.entidade.excecao;

public class LimiteExcedidoException extends RuntimeException{
    public LimiteExcedidoException(String mensagem){
        super(mensagem);
    }
}
