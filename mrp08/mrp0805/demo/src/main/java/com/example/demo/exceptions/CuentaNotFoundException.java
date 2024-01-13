package com.example.demo.exceptions;

public class CuentaNotFoundException extends RuntimeException{
    public CuentaNotFoundException(String iban){
        super("La cuenta con iban " + iban + " no existe");
    }
}
