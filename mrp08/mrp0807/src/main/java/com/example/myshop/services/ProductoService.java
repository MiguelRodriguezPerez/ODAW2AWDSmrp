package com.example.myshop.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.myshop.domain.Producto;

@Service
public interface ProductoService {
    Producto guardar(Producto producto);

    List<Producto> obtenerTodos();

    Producto obtenerPorId(long id);

    Producto editar(Producto producto);

    void borrar(Long id);
    
    public List<Producto> obtenerPorCategoria(Long id);

}
