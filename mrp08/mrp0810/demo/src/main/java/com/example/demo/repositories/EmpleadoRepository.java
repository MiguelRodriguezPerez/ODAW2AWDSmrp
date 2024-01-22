package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.domain.Empleado;

public interface EmpleadoRepository extends JpaRepository <Empleado, Long> {
    List<Empleado> findBySalarioGreaterThanEqualOrderBySalario(double salario);
    
    @Query("select e from Empleado e where e.nombre=?1 and e.email=?2")
    Empleado obtenerEmpleadoPorNombreYEmail (String nombre, String email);
}
