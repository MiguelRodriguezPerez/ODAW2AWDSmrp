package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Departamento;
import com.example.demo.repositories.DepartamentoRepository;

@Service
@Primary
public class DepartamentoServiceImplBD implements DepartamentoService{/*Implementa los metodos
    añadir, verTodos... */
    @Autowired
    DepartamentoRepository departamentoRepositorio;/*Al inyectar la interfaz que hereda JpaRepository
    tienes acceso a los métodos de la base de datos */

    @Override
    public List<Departamento> obtenerTodos() {
        return departamentoRepositorio.findAll();//Método del Jpa que devuelve todos los datos
    }

    @Override
    public Departamento nuevoDepartamento(Departamento d) {
        return departamentoRepositorio.save(d);//Método que guarda el objeto en la base de datos
    }

    @Override
    public Departamento obtenerDepartamentoPorId(Long id) {
        return departamentoRepositorio.findById(id).orElse(null);
        /*findById devuelve un objeto Optional que puede ser nulo
        orElse() es una función en la que defines que devolverá si findById() 
        devuelve nulo, significando .orElse(null) que devolverá nulo
        si no encuentra un departamento con ese id*/
    }

    @Override
    public Departamento editarDepartamento(Departamento departamento) {
        return departamentoRepositorio.save(departamento);
        /*Mismo método para guardar y editar departamentos */
    }

    @Override
    public void borrar(Long id) {
        departamentoRepositorio.deleteById(id);
        /*Borra por id el departamento */
    }

    public Departamento obtenerPorNombre(String nombre) {
        return departamentoRepositorio.findByNombre(nombre);
    }
}
