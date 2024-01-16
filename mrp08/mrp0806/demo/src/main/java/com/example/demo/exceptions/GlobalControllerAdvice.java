package com.example.demo.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@RestControllerAdvice
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler{
    /*Puede existir más de un controlador de errores, pero lo habitual es que todas las 
    excepciones las gestione un solo controlador*/
    /*Puedes establecer que un controlador de errores gestione los errores de un solo controller
    o de una clase con @ControllerAdvice("my.chosen.package")*/
    @ExceptionHandler(EmpleadoNotFoundException.class)
    public ResponseEntity<?> handleEmpleadoNotFound(EmpleadoNotFoundException ex,
    WebRequest webRequest){
        ExcepcionBody excepcionBody = new ExcepcionBody(LocalDateTime.now(), HttpStatus.NOT_FOUND,
        ex.getMessage(), ((ServletWebRequest) webRequest).getRequest().getRequestURI());
        return new ResponseEntity<Object>(excepcionBody,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmptyEmpleadosException.class)
    public ResponseEntity<?> handleEmptyEmpleados(EmptyEmpleadosException ex,
    WebRequest webRequest){
        ExcepcionBody excepcionBody = new ExcepcionBody(LocalDateTime.now(), HttpStatus.NOT_FOUND, 
        ex.getMessage(), ((ServletWebRequest) webRequest).getRequest().getRequestURI());       
        return new ResponseEntity<Object>(excepcionBody,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = Exception.class)//Solución obtenida por Bing
    //@Override Maneja todas las excepciones que no gestionan las otras dos anotaciones
    //Se supone que @Override debería funcionar y que esta es la manera correcta
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex,@Nullable Object body,
    HttpHeaders headers, HttpStatus status, WebRequest webRequest){
        ExcepcionBody excepcionBody = new ExcepcionBody(LocalDateTime.now(), status, 
        ex.getMessage(), ((ServletWebRequest) webRequest).getRequest().getRequestURI());
        return ResponseEntity.status(status).headers(headers).body(excepcionBody);
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
