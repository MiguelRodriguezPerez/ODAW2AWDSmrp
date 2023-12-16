package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Empleado;
import com.example.demo.domain.EmpleadoProyecto;
import com.example.demo.domain.Proyecto;


public interface EmpleadoProyectoRepository extends JpaRepository<EmpleadoProyecto,Long>{
    List<EmpleadoProyecto> findByEmpleado(Empleado empleado);
    List<EmpleadoProyecto> findByProyecto(Proyecto proyecto);
    EmpleadoProyecto findByEmpleadoAndProyecto(Empleado e, Proyecto p);
}
