package br.com.adalisadorquimico.adalisadorquimico.config;

import br.com.adalisadorquimico.adalisadorquimico.dto.ErrorDTO;
import br.com.adalisadorquimico.adalisadorquimico.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
@RestControllerAdvice
public class HandlerException {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({ProjetoNomeConflictException.class, MateriaPrimaDescricaoConflictException.class})
    public ErrorDTO handlerConflict(Exception ex) {
        return ErrorDTO.builder().message(ex.getMessage()).build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({ProjetoNotFoundException.class, MateriaPrimaNotFoundException.class})
    public ErrorDTO handlerNotFound(Exception ex) {
        return ErrorDTO.builder().message(ex.getMessage()).build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResultadoDRXInvalidoException.class)
    public ErrorDTO handlerResultadoDRXNotFound(ResultadoDRXInvalidoException ex) {
        return ErrorDTO.builder().message(ex.getMessage()).build();
    }
}
