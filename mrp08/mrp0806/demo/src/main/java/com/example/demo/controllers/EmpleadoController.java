package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.domain.Empleado;
import com.example.demo.exceptions.EmpleadoNotFoundException;
import com.example.demo.exceptions.EmptyEmpleadosException;
import com.example.demo.services.EmpleadoService;

import jakarta.validation.Valid;
@RestController
public class EmpleadoController {
  
  @Autowired
  public EmpleadoService empleadoService;

  @GetMapping("/empleado")
  public List<Empleado> getList(){
    List<Empleado> listaEmpleados = empleadoService.obtenerTodos();
    return listaEmpleados;
  }

  @GetMapping("/empleado/{id}")
  public Empleado getOneElement(@PathVariable Long id){
    Empleado empleado = empleadoService.obtenerPorId(id);
    return empleado;
    /*El código 200 (OK) es el que devuelve por defecto, que es el adecuado para 
    @GetMapping (acción GET) pero en las demás anotaciones(acciones) diferentes 
    tendrás que cambiarlo para que se adecuen  a su acción/anotación*/
  }

  @PostMapping("/empleado")
  public ResponseEntity<?> showNewSubmit(@Valid @RequestBody Empleado empleadoForm){
    //@Valid si no se cumple la validación devuelve BAD_REQUEST cod 400
    Empleado empleado = empleadoService.añadir(empleadoForm);
    return ResponseEntity.status(HttpStatus.CREATED).body(empleado);
    /*ResponseEntity.status(HttpStatus.CREATED); status() establece un código http personalizado
    cuyo argumento fija a CREATED con valor 201, body() establecerá el cuerpo de la respuesta*/
    /*Esta anotación/acción devolverá código 201 si se ejecuta correctamente */
  }

  @PutMapping("/empleado/{id}")//Mapping para actualizar datos del CRUD
  public Empleado editElement(@Valid @RequestBody Empleado editEmpleado, @PathVariable long id){
    empleadoService.obtenerPorId(id);
    //Al ejecutar esta función, se comprueba si produce una excepción
    return empleadoService.editar(editEmpleado);
    //Aparentemente, esto también devuelve código 200(OK)
  }

  @DeleteMapping("/empleado/{id}")//Mapping para borrar datos del CRUD
  public ResponseEntity<?> deleteElement(@PathVariable long id){
    Empleado empleado = empleadoService.obtenerPorId(id);
    if(empleado == null) return ResponseEntity.notFound().build();
    empleadoService.borrar(id);
    return ResponseEntity.noContent().build();
    /*Crea una instancia de ResponseEntity, noContent() le asigna el código 204 ('No Content') 
    y build() la crea*/
  }
}
