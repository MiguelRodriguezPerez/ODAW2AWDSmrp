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
import com.example.demo.domain.EmpleadoDTO;
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
    List<Empleado> listaEmpleados = empleadoService.obtenerTodos();
    List<EmpleadoDTO> listaDTO = empleadoService.convertEmpleadoToDto(listaEmpleados);
    model.addAttribute("listaEmpleados", listaDTO);
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
}
