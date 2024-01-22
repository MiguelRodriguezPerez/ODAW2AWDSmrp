package com.example.myshop.exceptions;

public class EmptyValoracionListException extends RuntimeException{
    public EmptyValoracionListException(){
        super("No hay valoraciones guardadas");
    }
}
