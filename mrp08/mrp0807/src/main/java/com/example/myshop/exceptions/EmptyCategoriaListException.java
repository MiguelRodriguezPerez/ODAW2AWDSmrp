package com.example.myshop.exceptions;

public class EmptyCategoriaListException extends RuntimeException {
   public EmptyCategoriaListException() {
      super("No hay categorias almacenadas");
   }
}
