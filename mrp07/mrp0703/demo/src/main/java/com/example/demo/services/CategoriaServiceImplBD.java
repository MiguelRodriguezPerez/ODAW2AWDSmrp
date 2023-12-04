package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Categoria;
import com.example.demo.repositories.CategoriaRepository;

@Service
@Primary
public class CategoriaServiceImplBD implements CategoriaService{/*Implementa los metodos
    añadir, verTodos... */
    @Autowired
    CategoriaRepository categoriaRepositorio;/*Al inyectar la interfaz que hereda JpaRepository
    tienes acceso a los métodos de la base de datos */

    @Override
    public List<Categoria> obtenerTodos() {
        return categoriaRepositorio.findAll();//Método del Jpa que devuelve todos los datos
    }

    @Override
    public Categoria nuevaCategoria(Categoria d) {
        return categoriaRepositorio.save(d);//Método que guarda el objeto en la base de datos
    }

    @Override
    public Categoria obtenerCategoriaPorId(Long id) {
        return categoriaRepositorio.findByIdCategoria(id).orElse(null);
        /*findById devuelve un objeto Optional que puede ser nulo
        orElse() es una función en la que defines que devolverá si findById() 
        devuelve nulo, significando .orElse(null) que devolverá nulo
        si no encuentra un Categoria con ese id*/
    }

    @Override
    public Categoria editarCategoria(Categoria Categoria) {
        return categoriaRepositorio.save(Categoria);
        /*Mismo método para guardar y editar Categorias */
    }

    @Override
    public void borrar(Long id) {
        categoriaRepositorio.deleteById(id);
        /*Borra por id el Categoria */
    }

    public Categoria obtenerPorNombre(String nombre) {
        return categoriaRepositorio.findByNombre(nombre);
    }
}
