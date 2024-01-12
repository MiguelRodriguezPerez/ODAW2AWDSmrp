package com.example.demo.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.domain.Empleado;
import com.example.demo.services.DepartamentoService;

@Component
public class EmpleadoDTOConverter {
@Autowired
DepartamentoService departamentoService;
public Empleado convertDtoToEmpleado(EmpleadoNuevoDTO empleadoNuevoDTO) {
    return new Empleado(4l,
    empleadoNuevoDTO.getNombre(),
    empleadoNuevoDTO.getEmail(),
    empleadoNuevoDTO.getSalario(),
    empleadoNuevoDTO.isEnActivo(),
    empleadoNuevoDTO.getGenero(),
    departamentoService.obtenerDepartamentoPorId(empleadoNuevoDTO.getDepartamentoId()));
}
public Empleado convertDtoToEmpleado(EmpleadoNuevoDTO empleadoNuevoDTO, Long id) {
    return new Empleado(
    id,
    empleadoNuevoDTO.getNombre(),
    empleadoNuevoDTO.getEmail(),
    empleadoNuevoDTO.getSalario(),
    empleadoNuevoDTO.isEnActivo(),
    empleadoNuevoDTO.getGenero(),
    departamentoService.obtenerDepartamentoPorId(empleadoNuevoDTO.getDepartamentoId()));
    }
}
