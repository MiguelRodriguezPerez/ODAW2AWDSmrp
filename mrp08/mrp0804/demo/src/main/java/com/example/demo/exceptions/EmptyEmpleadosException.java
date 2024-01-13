package com.example.demo.exceptions;

public class EmptyEmpleadosException extends RuntimeException{
    public EmptyEmpleadosException(){
        super("No hay empleados en el sistema");
    }
}
