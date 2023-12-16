package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Proyecto;
import com.example.demo.services.ProyectoService;

@RequestMapping("/proy")
@Controller
public class ProyectoController {
    
    @Autowired
    ProyectoService proyectoService;

    @GetMapping("/")
    public String showProyectosIndex(Model model){
        model.addAttribute("listaProyectos", proyectoService.obtenerTodos());
        return "proyectoFolder/proyectosView";
    }

    @GetMapping("/new")
    public String showProyectosNuevo(Model model){
        model.addAttribute("proyecto", new Proyecto());
        return "proyectoFolder/proyectoNew";
    }

    @PostMapping("/nuevoProyecto/submit")
    public String showProyectosNuevoSubmit(Proyecto proyecto){
        proyectoService.nuevoProyecto(proyecto);
        return "redirect:/proy/";
    }

    @GetMapping("/edit/{id}")
    public String showProyectosEditar(@PathVariable Long id,Model model){
        System.out.println(id);
        model.addAttribute("proyecto", proyectoService.encontrarPorId(id));
        return "proyectoFolder/proyectoEdit";
    }

    @PostMapping("/edit/submit")
    public String showProyectosEditarSubmit(Proyecto proyecto){
        System.out.println(proyecto.getId());
        proyectoService.editarProyecto(proyecto);
        return "redirect:/proy/";
    }

    @GetMapping("/delete/{id}")
    public String showProyectosBorrar(@PathVariable Long id){
        proyectoService.borrarProyecto(id);
        return "redirect:/proy/";
    }
}
