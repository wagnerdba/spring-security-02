package com.wrtecnologia.clientnoauthapplication;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Object> handleResponseStatusException(ResponseStatusException ex) {
        // Retornar a mensagem de erro com o status apropriado
        return new ResponseEntity<>(new ErrorMessage(ex.getReason()), ex.getStatusCode());
    }
}