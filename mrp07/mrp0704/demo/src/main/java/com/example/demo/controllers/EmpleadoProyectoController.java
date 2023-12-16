package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Empleado;
import com.example.demo.domain.EmpleadoProyecto;
import com.example.demo.domain.Proyecto;
import com.example.demo.services.ProyectoService;
import com.example.demo.services.Empleado.EmpleadoProyectoService;
import com.example.demo.services.Empleado.EmpleadoService;

import jakarta.validation.Valid;

@RequestMapping("/emplProy")
@Controller
public class EmpleadoProyectoController {
    
    @Autowired
    public EmpleadoProyectoService empleadoProyectoService;
    @Autowired
    public EmpleadoService empleadoService;
    @Autowired
    public ProyectoService proyectoService;

    @GetMapping("/emp/{id}") // lista de proyectos de un empleado
    public String showProyectsByEmpl(@PathVariable long id, Model model) {
        Empleado e = empleadoService.obtenerPorId(id);
        model.addAttribute("listaEmpleadoProyecto",
        empleadoProyectoService.obtenerPorEmpleado(e));
        model.addAttribute("empleado", empleadoService.obtenerPorId(id));
        return "empleadoProyectoFolder/empListView";
    }

    @GetMapping("/pro/{id}") // lista de empleados de un proyecto
    public String showEmplbyProyect(@PathVariable long id, Model model) {
        Proyecto p = proyectoService.encontrarPorId(id);
        model.addAttribute("listaEmpleadoProyecto",
        empleadoProyectoService.obtenerPorProyecto(p));
        model.addAttribute("proyecto", proyectoService.encontrarPorId(id));
        return "empleadoProyectoFolder/proListView";
    }

    @GetMapping("/delete/{id}")
    public String showDeleteEmpl(@PathVariable long id) {
        empleadoProyectoService.borrar(empleadoProyectoService.obtenerPorId(id));
        return "redirect:/";
    }

    @GetMapping("/new")
    public String showNewProjectEmpl(Model model) {
        model.addAttribute("empleadoProyectoForm", new EmpleadoProyecto());
        model.addAttribute("listaEmpleados", empleadoService.obtenerTodos());
        model.addAttribute("listaProyectos", proyectoService.obtenerTodos());
        return "empleadoProyectoFolder/empProyNewFormView";
    }
    @PostMapping("/new/submit")
    public String showNewProjectEmplSubmit(@Valid EmpleadoProyecto empleadoProyectoForm,
    BindingResult bindingResult) {
        if (!bindingResult.hasErrors()){System.out.println("Error");}
        empleadoProyectoService.añadir(empleadoProyectoForm);
        return "redirect:/";
    }
}
