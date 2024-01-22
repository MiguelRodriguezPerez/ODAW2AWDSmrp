package com.example.myshop.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myshop.domain.Categoria;
import com.example.myshop.exceptions.EmptyCategoriaListException;
import com.example.myshop.repositories.CategoriaRepository;

@Service
public class CategoriaServiceImplMem implements CategoriaService {

    @Autowired
    public ProductoService productoService;

    @Autowired
    CategoriaRepository categoriaRepositorio;


    public Categoria añadir(Categoria categoria) {
        return categoriaRepositorio.save(categoria);
       
    }

    public List<Categoria> obtenerTodos() {
        List<Categoria> lista = categoriaRepositorio.findAll();
        if(lista.isEmpty()) throw new EmptyCategoriaListException();
        else return lista;
    }

    public Categoria obtenerPorId(long id) {
        return categoriaRepositorio.findById(id).orElse(null);
    }

    public Categoria editar(Categoria categoria) {
        return categoriaRepositorio.save(categoria);
       
    }

    public void borrar(Long id) {
        categoriaRepositorio.deleteById(id);
    }

    @Override
    public void borrarTodo() {
        categoriaRepositorio.deleteAll();
    }
}
