package com.example.demo.exceptions;

public class EmptyCuentasListException extends RuntimeException{
    public EmptyCuentasListException(){
        super("No hay cuentas en la base de datos");
    }
}
