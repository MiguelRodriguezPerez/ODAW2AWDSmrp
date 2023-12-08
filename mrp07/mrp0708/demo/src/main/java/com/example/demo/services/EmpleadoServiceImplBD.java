package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Empleado;
import com.example.demo.domain.EmpleadoDTO;
import com.example.demo.repositories.EmpleadoRepository;

@Service
@Primary
public class EmpleadoServiceImplBD implements EmpleadoService {
    @Autowired
    EmpleadoRepository repositorio;
    @Autowired
    ModelMapper modelMapper;
    

    public List<EmpleadoDTO> convertEmpleadoToDto(List<Empleado> listaEmpleados) {
        List<EmpleadoDTO> listaEmpleadoDTO = new ArrayList<>();
        for (Empleado empleado : listaEmpleados){
            System.out.println(empleado.getDepartamento().getNombre());//Recibe correctamente los nombres
            listaEmpleadoDTO.add(modelMapper.map(empleado, EmpleadoDTO.class));
        }
        return listaEmpleadoDTO;
    }

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

}