package com.example.demo.services;

import java.util.List;

import com.example.demo.domain.Proyecto;

public interface ProyectoService {
    List<Proyecto> obtenerTodos();
    Proyecto nuevoProyecto(Proyecto p);
    Proyecto editarProyecto(Proyecto p);
    void borrarProyecto(Long id);
    Proyecto encontrarPorId(Long id);
}
