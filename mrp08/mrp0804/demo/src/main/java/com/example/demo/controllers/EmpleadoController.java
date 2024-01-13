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
    List<Empleado> listaEmpleados;
    try{
      listaEmpleados = empleadoService.obtenerTodos();
    }
    catch(EmptyEmpleadosException e){
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
    }
    return listaEmpleados;
  }

  @GetMapping("/empleado/{id}")
  public Empleado getOneElement(@PathVariable Long id){
    Empleado empleado;
    try{
      empleado = empleadoService.obtenerPorId(id);
    }
    catch(EmpleadoNotFoundException e){
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
    }
    return empleado;
  }

  @PostMapping("/empleado")
  public ResponseEntity<?> showNewSubmit(@Valid @RequestBody Empleado empleadoForm){
    //@Valid si no se cumple la validación devuelve BAD_REQUEST cod 400
    Empleado empleado = empleadoService.añadir(empleadoForm);
    return ResponseEntity.status(HttpStatus.CREATED).body(empleado);
    /*ResponseEntity.status(HttpStatus.CREATED); status() establece un código http personalizado
    cuyo argumento fija a CREATED con valor 201, body() establecerá el cuerpo de la respuesta*/
  }

  @PutMapping("/empleado/{id}")//Mapping para actualizar datos del CRUD
  public ResponseEntity<?> editElement(@Valid @RequestBody Empleado editEmpleado, @PathVariable long id){
    Empleado empleado = empleadoService.obtenerPorId(id);
    //el commandobject del formulario es el empleado con el id solicitado
    if(empleado == null) return ResponseEntity.notFound().build();
    else {
      empleado = empleadoService.editar(editEmpleado);
      return ResponseEntity.ok(empleado);
    }
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

//   @GetMapping("/listado1/{salario}")
//   public String showListado1(@PathVariable Double salario, Model model) {
//     List<Empleado> empleados = empleadoService.obtenerEmpleadosSalarioMayor (salario);
//     model.addAttribute("tituloListado", "Empleados salario mayor que" +
//     salario.toString());
//     model.addAttribute("listaEmpleados", empleados);
//     return "listadosView";
// }

//   @GetMapping("/listado2")
//   public String showListado2(Model model) {
//     List<Empleado> empleados = empleadoService.obtenerEmpleadoSalarioMayorMedia();
//     model.addAttribute("tituloListado", "Empleados salario mayor que la media");
//     model.addAttribute("listaEmpleados", empleados);
//     return "listadosView";
//   }
}
