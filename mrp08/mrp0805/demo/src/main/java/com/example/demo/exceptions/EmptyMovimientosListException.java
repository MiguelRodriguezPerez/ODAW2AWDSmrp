package com.example.demo.exceptions;

public class EmptyMovimientosListException extends RuntimeException{
    public EmptyMovimientosListException(String iban){
        super("La cuenta con iban " + iban + " no tiene movimientos");
    }
}
