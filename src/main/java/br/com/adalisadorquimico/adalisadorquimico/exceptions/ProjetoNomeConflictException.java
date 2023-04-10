package br.com.adalisadorquimico.adalisadorquimico.exceptions;

public class ProjetoNomeConflictException extends RuntimeException{
    public ProjetoNomeConflictException() {
        super("Objeto já existe");
    }
}
