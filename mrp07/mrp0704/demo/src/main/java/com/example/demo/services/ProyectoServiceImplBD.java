package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Proyecto;
import com.example.demo.repositories.ProyectoRepository;

@Service
public class ProyectoServiceImplBD implements ProyectoService{

    @Autowired
    ProyectoRepository proyectoRepository;

    @Override
    public List<Proyecto> obtenerTodos(){
        return proyectoRepository.findAll();
    }

    @Override
    public Proyecto nuevoProyecto(Proyecto p) {
        return proyectoRepository.save(p);
    }

    @Override
    public Proyecto editarProyecto(Proyecto p) {
        return proyectoRepository.save(p);
    }

    @Override
    public void borrarProyecto(Long id) {
        proyectoRepository.deleteById(id);
    }

    @Override
    public Proyecto encontrarPorId(Long id) {
        return proyectoRepository.findById(id).orElse(null);
    }
    
}
