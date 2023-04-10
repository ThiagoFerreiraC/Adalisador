package br.com.adalisadorquimico.adalisadorquimico.exceptions;

public class MateriaPrimaNotFoundException extends RuntimeException{
    public MateriaPrimaNotFoundException() {
        super("Objeto não encontrado");
    }
}
