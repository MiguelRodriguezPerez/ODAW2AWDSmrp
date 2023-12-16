package com.example.demo.services;

import java.util.List;

import com.example.demo.domain.Categoria;

public interface CategoriaService {//Separa la interfaz de su implementación
    List<Categoria> obtenerTodos();
    Categoria nuevaCategoria(Categoria Categoria);
    Categoria obtenerCategoriaPorId(Long id);
    Categoria editarCategoria(Categoria Categoria);
    void borrar(Long id);
    Categoria obtenerPorNombre(String nombre);
}
