package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
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

import com.example.demo.domain.Empleado;
import com.example.demo.dto.EmpleadoDTO;
import com.example.demo.dto.EmpleadoDTOConverter;
import com.example.demo.dto.EmpleadoNuevoDTO;
import com.example.demo.services.DepartamentoService;
import com.example.demo.services.EmpleadoService;

import jakarta.validation.Valid;
@RestController
public class EmpleadoController {
  
  @Autowired
  EmpleadoService empleadoService;
  @Autowired
  DepartamentoService departamentoService;
  @Autowired
  ModelMapper modelMapper;
  @Autowired
  EmpleadoDTOConverter empleadoDTOConverter;

  @GetMapping("/empleado")
  public ResponseEntity<?> getList(){
    List<Empleado> listaEmpleados = empleadoService.obtenerTodos();
    if(listaEmpleados.isEmpty()) return ResponseEntity.notFound().build();
    else{
      List<EmpleadoDTO> listaEmpleadoDTO = new ArrayList<>();
      for(Empleado e: listaEmpleados){
        listaEmpleadoDTO.add(modelMapper.map(e,EmpleadoDTO.class));
      }
      return ResponseEntity.ok(listaEmpleadoDTO);
    }
  }

  @PostMapping("/empleado")
  public ResponseEntity<?> newEmpleado(@Valid @RequestBody EmpleadoNuevoDTO empleadoNuevoDTO){
    Empleado empleado = empleadoDTOConverter.convertDtoToEmpleado(empleadoNuevoDTO);
    Empleado empleadoSaved = empleadoService.añadir(empleado);
    return ResponseEntity.status(HttpStatus.CREATED).body(empleadoSaved); // cod 201
  }

  @GetMapping("/empleado/{id}")
  public ResponseEntity<?> getOneElement(@PathVariable Long id){
    Empleado empleado = empleadoService.obtenerPorId(id);
    if(empleado == null) return ResponseEntity.notFound().build();
    else return ResponseEntity.ok(empleado);
  }

  @PutMapping("/empleado/{id}")//Mapping para actualizar datos del CRUD
  public ResponseEntity<?> editElement(@RequestBody EmpleadoNuevoDTO editEmpleado,
    @PathVariable Long id){
    Empleado empleado = empleadoService.obtenerPorId(id);
      if (empleado == null) return ResponseEntity.notFound().build();
      else {
        empleado = empleadoDTOConverter.convertDtoToEmpleado(editEmpleado, id);
        Empleado empleadoSaved = empleadoService.editar(empleado);
        return ResponseEntity.ok(empleadoSaved);
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
