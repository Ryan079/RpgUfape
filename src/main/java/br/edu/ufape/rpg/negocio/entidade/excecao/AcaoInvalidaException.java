package br.edu.ufape.rpg.negocio.entidade.excecao;

public class AcaoInvalidaException extends RuntimeException{
    public AcaoInvalidaException(String mensagem){
        super(mensagem);
    }
}
