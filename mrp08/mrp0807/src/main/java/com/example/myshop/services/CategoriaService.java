package com.example.myshop.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.myshop.domain.Categoria;

@Service
public interface CategoriaService {
    Categoria añadir(Categoria categoria);

    List<Categoria> obtenerTodos();

    Categoria obtenerPorId(long id);

    Categoria editar(Categoria categoria);

    void borrar(Long id);

    void borrarTodo();
}
