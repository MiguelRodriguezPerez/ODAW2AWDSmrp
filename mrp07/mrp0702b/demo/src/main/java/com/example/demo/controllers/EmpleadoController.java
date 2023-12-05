package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.domain.Empleado;
import com.example.demo.services.DepartamentoService;
import com.example.demo.services.EmpleadoService;

import jakarta.validation.Valid;
@Controller
public class EmpleadoController {
  
  @Autowired
  EmpleadoService empleadoService;
  @Autowired
  DepartamentoService departamentoService;

  @GetMapping({"/","/list"})
  public String showList(Model model){
    model.addAttribute("listaEmpleados", empleadoService.obtenerTodos());
    model.addAttribute("listaDepartamentos", departamentoService.obtenerTodos());
    model.addAttribute("deptoSeleccionado", 0);
    return "empleadosFolder/empleadosView";
  }

  @GetMapping("/nuevo")
  public String showNew(Model model){
    //el commandobject del formulario es una instancia de empleado vacía
    model.addAttribute("empleadoForm", new Empleado());
    model.addAttribute("listaDepartamentos",departamentoService.obtenerTodos());
    return "empleadosFolder/nuevoEmpleado";
  }

  @PostMapping("/nuevo/submit")
  public String showNewSubmit(@Valid Empleado empleadoForm, BindingResult bindingResult){
    if(bindingResult.hasErrors()) return "redirect:/nuevo";
    empleadoService.añadir(empleadoForm);
    return "redirect:/list";
  }

  @GetMapping("/editar/{id}")
  public String showEditForm(@PathVariable long id, Model model){
    Empleado empleado = empleadoService.obtenerPorId(id);
    //el commandobject del formulario es el empleado con el id solicitado
    if(empleado != null){
      model.addAttribute("empleadoForm", empleado);
      model.addAttribute("listaDepartamentos",departamentoService.obtenerTodos());
      return "empleadosFolder/editarEmpleado";
    }
    return "redirect:/";
  }

  @PostMapping("/editar/submit")
  public String showEditSubmit(@Valid Empleado empleadoForm,BindingResult bindingResult){
    if(!bindingResult.hasErrors()) empleadoService.editar(empleadoForm);
      return "redirect:/list";
  }

  @GetMapping("/borrar/{id}")
  public String showDelete(@PathVariable long id){
    empleadoService.borrar(id);
    return "redirect:/list";
  }

  @GetMapping("/listado1/{salario}")
  public String showListado1(@PathVariable Double salario, Model model) {
    List<Empleado> empleados = empleadoService.obtenerEmpleadosSalarioMayor (salario);
    model.addAttribute("tituloListado", "Empleados salario mayor que " +
    salario.toString());
    model.addAttribute("listaEmpleados", empleados);
    return "empleadosFolder/verEmpleadosPorSalario";
}

  @GetMapping("/listado2")
  public String showListado2(Model model) {
    List<Empleado> empleados = empleadoService.obtenerEmpleadoSalarioMayorMedia();
    model.addAttribute("tituloListado", "Empleados salario mayor que la media");
    model.addAttribute("listaEmpleados", empleados);
    return "empleadosFolder/verEmpleadosPorSalario";
  }

  @GetMapping("/porDepto/{id}")
  public String showPorDepto(@PathVariable Long id, Model model){
    model.addAttribute("listaEmpleados",empleadoService.obtenerPorDepartamento(departamentoService.obtenerDepartamentoPorId(id)));
    model.addAttribute("listaDepartamentos", departamentoService.obtenerTodos());
    model.addAttribute("deptoSeleccionado", id);
    return "empleadosFolder/porDepartamento";
  }
}
