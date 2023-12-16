package com.example.demo.services.Empleado;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Empleado;
import com.example.demo.domain.EmpleadoProyecto;
import com.example.demo.domain.Proyecto;
import com.example.demo.repositories.EmpleadoProyectoRepository;

@Service
public class EmpleadoProyectoServiceImplBD implements EmpleadoProyectoService{

    @Autowired
    EmpleadoProyectoRepository epr;


    @Override
    public EmpleadoProyecto obtenerPorId(Long id) {
        return epr.findById(id).orElse(null);
    }

    @Override
    public EmpleadoProyecto añadir(EmpleadoProyecto empleadoProyecto) {
        return epr.save(empleadoProyecto);
    }

    @Override
    public void borrar(EmpleadoProyecto empleadoProyecto) {
        epr.delete(empleadoProyecto);
    }

    @Override
    public List<EmpleadoProyecto> obtenerPorEmpleado(Empleado empleado) {
        return epr.findByEmpleado(empleado);
    }

    @Override
    public List<EmpleadoProyecto> obtenerPorProyecto(Proyecto proyecto) {
        return epr.findByProyecto(proyecto);
    }

    @Override
    public EmpleadoProyecto obtenerPorEmpleadoProyecto(Empleado e, Proyecto p) {
        return epr.findByEmpleadoAndProyecto(e, p);
    }
    
}
