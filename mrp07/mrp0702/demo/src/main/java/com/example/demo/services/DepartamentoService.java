package com.example.demo.services;

import java.util.List;

import com.example.demo.domain.Departamento;

public interface DepartamentoService {//Separa la interfaz de su implementación
    List<Departamento> obtenerTodos();
    Departamento nuevoDepartamento(Departamento departamento);
    Departamento obtenerDepartamentoPorId(Long id);
    Departamento editarDepartamento(Departamento departamento);
    void borrar(Long id);
    Departamento obtenerPorNombre(String nombre);
}
