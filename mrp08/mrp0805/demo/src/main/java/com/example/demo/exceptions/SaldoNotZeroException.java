package com.example.demo.exceptions;

public class SaldoNotZeroException extends RuntimeException{
    public SaldoNotZeroException(String iban){
        super("La cuenta con iban " + iban + " no puede ser borrada porque su saldo no es 0");
    }
}
