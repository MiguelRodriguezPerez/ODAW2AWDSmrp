package com.example.myshop.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myshop.domain.Categoria;
import com.example.myshop.domain.Producto;
import com.example.myshop.repositories.CategoriaRepository;
import com.example.myshop.repositories.ProductoRepository;

@Service
public class ProductoServiceImplMem implements ProductoService {
    @Autowired
    ProductoRepository productoRepositorio;

    @Autowired
    CategoriaRepository categoriaRepositorio;

    public Producto añadir(Producto producto) {
        return productoRepositorio.save(producto);
    }

    public List<Producto> obtenerTodos() {
        return productoRepositorio.findAll();
    }

    public Producto obtenerPorId(long id) {
      return productoRepositorio.findById(id).orElse(null);
    }

    public Producto editar(Producto producto) {
        return productoRepositorio.save(producto);
    }

    public void delete(Long id) {
        productoRepositorio.deleteById(id);
    }

       public List<Producto> obtenerPorCategoria(Long idCategoria){

        List<Producto> productosPorCategoria = new ArrayList<>();
        Categoria categoria =  categoriaRepositorio.findById(idCategoria).orElse(null);
        if (categoria==null) return null;
        return productoRepositorio.findByCategoria(categoria);

    }


}
