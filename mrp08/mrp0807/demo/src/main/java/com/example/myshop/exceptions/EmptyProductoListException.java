package com.example.myshop.exceptions;

public class EmptyProductoListException extends RuntimeException{
    public EmptyProductoListException(){
        super("No hay productos almacenados");
    }
}
