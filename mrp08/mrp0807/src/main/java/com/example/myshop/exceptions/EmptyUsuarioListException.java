package com.example.myshop.exceptions;

public class EmptyUsuarioListException extends RuntimeException{

    public EmptyUsuarioListException(){
        super("No hay usuarios guardados");
    }
}