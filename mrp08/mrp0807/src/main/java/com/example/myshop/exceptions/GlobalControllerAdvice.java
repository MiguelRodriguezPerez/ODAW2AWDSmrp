package com.example.myshop.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@RestControllerAdvice
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmptyCategoriaListException.class)
    public ResponseEntity<?> emptyListExceptionCategoria(EmptyCategoriaListException ex, WebRequest webRequest) {
        ExcepcionBody excepcionBody = new ExcepcionBody(LocalDateTime.now(),
        HttpStatus.NOT_FOUND,
        ex.getMessage(),
        ((ServletWebRequest) webRequest).getRequest().getRequestURI());

        return new ResponseEntity<Object>(excepcionBody, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmptyProductoListException.class)
    public ResponseEntity<?> emptyListExceptionProducto(EmptyProductoListException ex, WebRequest webRequest){
        ExcepcionBody excepcionBody = new ExcepcionBody(LocalDateTime.now(),
        HttpStatus.NOT_FOUND, 
        ex.getMessage(), 
        ((ServletWebRequest) webRequest).getRequest().getRequestURI());

        return new ResponseEntity<Object>(excepcionBody,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmptyUsuarioListException.class)
    public ResponseEntity<?> emptyUsuarioList(EmptyUsuarioListException ex, WebRequest webRequest){
        ExcepcionBody excepcionBody = new ExcepcionBody(LocalDateTime.now(),
        HttpStatus.NOT_FOUND,
        ex.getMessage(), 
        ((ServletWebRequest) webRequest).getRequest().getRequestURI());

        return new ResponseEntity<Object>(excepcionBody,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmptyValoracionListException.class)
    public ResponseEntity<?> emptyValoracionException(EmptyValoracionListException ex, WebRequest webRequest){
        ExcepcionBody excepcionBody = new ExcepcionBody(LocalDateTime.now(),
        HttpStatus.NOT_FOUND,
        ex.getMessage(),
        ((ServletWebRequest)webRequest).getRequest().getRequestURI());

        return new ResponseEntity<>(excepcionBody,HttpStatus.NOT_FOUND);
    }
}

@AllArgsConstructor
@Getter
class ExcepcionBody {
    private LocalDateTime timestamp;
    private HttpStatus status;
    private String message;
    private String path;
}