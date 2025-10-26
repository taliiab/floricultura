package br.csi.floricultura.controller;

import br.csi.floricultura.service.exception.RecursoNaoEncontradoException;
import br.csi.floricultura.service.exception.RegraDeNegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<?> recursoNaoEncontrado(RecursoNaoEncontradoException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(RegraDeNegocioException.class)
    public ResponseEntity<?> regraDeNegocio(RegraDeNegocioException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {

        // cria um mapa para guardar os erros (campo -> mensagem)
        Map<String, String> errors = new HashMap<>();

        // passa sobre todos os erros de campo da exceção
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField(); // nome
            String errorMessage = error.getDefaultMessage(); // mensagem
            errors.put(fieldName, errorMessage); // add ao mapa
        });

        // retorna o mapa com status 400
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
