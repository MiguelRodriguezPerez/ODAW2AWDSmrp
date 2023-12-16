package com.example.demo.services.Empleado;

import java.util.List;

import com.example.demo.domain.Empleado;
import com.example.demo.domain.EmpleadoProyecto;
import com.example.demo.domain.Proyecto;

public interface EmpleadoProyectoService {
    EmpleadoProyecto obtenerPorId(Long id);
    EmpleadoProyecto añadir(EmpleadoProyecto empleadoProyecto);
    void borrar(EmpleadoProyecto empleadoProyecto);
    List<EmpleadoProyecto> obtenerPorEmpleado(Empleado empleado);
    List<EmpleadoProyecto> obtenerPorProyecto(Proyecto proyecto);
    EmpleadoProyecto obtenerPorEmpleadoProyecto(Empleado e, Proyecto p);
}
