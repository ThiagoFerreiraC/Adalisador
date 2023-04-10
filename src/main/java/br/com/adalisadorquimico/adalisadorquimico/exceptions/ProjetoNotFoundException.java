package br.com.adalisadorquimico.adalisadorquimico.exceptions;

public class ProjetoNotFoundException extends RuntimeException{
    public ProjetoNotFoundException() {
        super("Objeto não encontrado");
    }
}
