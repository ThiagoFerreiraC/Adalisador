package br.com.adalisadorquimico.adalisadorquimico.exceptions;

public class MateriaPrimaDescricaoConflictException extends RuntimeException{
    public MateriaPrimaDescricaoConflictException() {
        super("Objeto já existe");
    }
}
