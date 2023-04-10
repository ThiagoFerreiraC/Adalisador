package br.com.adalisadorquimico.adalisadorquimico.exceptions;

public class ResultadoDRXInvalidoException extends RuntimeException{
    public ResultadoDRXInvalidoException() {
        super("Um dos resultados não é um composto válido");
    }
}
