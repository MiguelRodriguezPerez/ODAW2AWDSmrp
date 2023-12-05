package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.domain.Departamento;
import com.example.demo.domain.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    List<Empleado> findBySalarioGreaterThanEqualOrderBySalario (double salario);
    List<Empleado> findByDepartamento(Departamento departamento);
    /*Fija con @Query la consulta que hará el método al que se le inserte la anotación*/
    @Query("select e from Empleado e " +
    "where e.salario > (select avg (e2.salario) from Empleado e2)")
    List <Empleado> obtenerEmpleadoSalarioMayorMedia();
}
