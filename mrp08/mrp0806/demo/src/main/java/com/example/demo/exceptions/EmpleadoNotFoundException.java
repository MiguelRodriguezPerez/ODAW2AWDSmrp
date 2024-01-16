package com.example.demo.exceptions;

public class EmpleadoNotFoundException extends RuntimeException{
    public EmpleadoNotFoundException(Long id){
        super("No se puede encontrar el empleado con id " + id);
    }
}
