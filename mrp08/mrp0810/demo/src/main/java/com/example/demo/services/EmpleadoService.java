package com.example.demo.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Empleado;
import com.example.demo.repositories.EmpleadoRepository;

@Service
public class EmpleadoService {
    
    @Autowired
    EmpleadoRepository repositorio;

    public Empleado añadir(Empleado empleado) {
        repositorio.save(empleado);
        return empleado;

        //return repositorio.save(empleado);
    }

    public List<Empleado> obtenerTodos() {
        return repositorio.findAll();
    }

    public Empleado obtenerPorId(long id) {
        return repositorio.findById(id).orElse(null);
    }

    public Empleado editar(Empleado empleado) {
        return repositorio.save(empleado);
    }

    public void borrar(Long id) {
        //repositorio.deleteById(id);
        Empleado empleado = repositorio.findById(id).orElse(null);
        if (empleado != null)
        repositorio.delete(empleado);
    }
}
