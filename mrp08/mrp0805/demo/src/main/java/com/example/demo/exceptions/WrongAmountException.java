package com.example.demo.exceptions;

public class WrongAmountException extends RuntimeException{
    public WrongAmountException(Double importe){
        super("El importe " + importe + " es mayor que 1000 o menor que 300");
    }
}
