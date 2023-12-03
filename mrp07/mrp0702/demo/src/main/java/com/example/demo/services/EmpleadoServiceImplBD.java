package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Empleado;
import com.example.demo.repositories.EmpleadoRepository;

@Service
@Primary
public class EmpleadoServiceImplBD implements EmpleadoService {
    @Autowired
    EmpleadoRepository repositorio;
    public Empleado añadir (Empleado empleado) {
     return repositorio.save (empleado);
    }
    public List<Empleado> obtenerTodos() { 
        return repositorio.findAll (); 
    }

    public Empleado obtenerPorId (long id) {
    return repositorio.findById (id).orElse(null);
    // findById de JpaRepository devuelve un Optional. Para simplificar,
    // y que el servicio siga devolviendo Empleado y no Optional<Empleado>
    // hacemos que si no lo encuentra devuelva null.
    }
    public Empleado editar (Empleado empleado) {
        return repositorio.save (empleado);
    }
    public void borrar(Long id) {
        repositorio.deleteById (id);
    }

    public List<Empleado> obtenerEmpleadosSalarioMayor (double salario){
        return repositorio.findBySalarioGreaterThanEqualOrderBySalario(salario);
    }
    public List<Empleado> obtenerEmpleadoSalarioMayorMedia() {
        return repositorio.obtenerEmpleadoSalarioMayorMedia();
    }
}