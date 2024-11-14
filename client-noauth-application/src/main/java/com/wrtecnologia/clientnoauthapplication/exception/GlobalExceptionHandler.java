package com.wrtecnologia.clientnoauthapplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String MESSAGE_UNAUTHORIZED = "Unauthorized access to Lessons API";
    private static final String MESSAGE_INTERNAL_SERVER_ERROR = "Internal Server Error occurred in Lessons API";
    private static final String MESSAGE_URL_NOT_EXIST = "Requested resource not found on Lessons API";
    private static final String MESSAGE_GENERIC_ERROR = "An error occurred";

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Object> handleResponseStatusException(ResponseStatusException ex) {
        String message = getCustomErrorMessage(ex.getStatusCode());
        return new ResponseEntity<>(new ErrorMessage(ex.getStatusCode().toString(), message), ex.getStatusCode());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Object> handleNoHandlerFoundException() {
        return new ResponseEntity<>(new ErrorMessage(HttpStatus.NOT_FOUND.toString(), MESSAGE_URL_NOT_EXIST), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleInternalServerError() {
        return new ResponseEntity<>(new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.toString(), MESSAGE_GENERIC_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String getCustomErrorMessage(HttpStatusCode statusCode) {
        if (statusCode == HttpStatus.UNAUTHORIZED) {
            return MESSAGE_UNAUTHORIZED;
        } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR) {
            return MESSAGE_INTERNAL_SERVER_ERROR;
        } else {
            return MESSAGE_GENERIC_ERROR;
        }
    }
}
