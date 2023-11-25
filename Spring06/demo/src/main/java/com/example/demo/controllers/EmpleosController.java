package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.domain.Empleo;
import com.example.demo.services.EmpleosService;

import jakarta.annotation.PostConstruct;

@Controller
public class EmpleosController {

    @Autowired
    EmpleosService empleosService;
    
    @GetMapping("/empleosDisponibles")
    public String showEmpleos(Model model){
        model.addAttribute("listaEmpleos", empleosService.obtEmpleos());
        return "empleos/empleosDisponibles";
    }

    @GetMapping("/nuevoEmpleo")
    public String nuevoEmpleo(Model model){
        model.addAttribute("nEmpleo", new Empleo());
        return "empleos/nuevoEmpleo";
    }

    @PostMapping("nuevoEmpleo/submit")
    public String nuevoEmpleoSubmit(Empleo empleo){
        empleosService.otroEmpleo(empleo);
        return "redirect:/empleosDisponibles";
    }

    @GetMapping("/borrar/{id}")
    public String borrarEmpleoView(@PathVariable Long id){
        empleosService.borrarEmpleo(id);
        return "redirect:/empleosDisponibles";
    }
    @GetMapping("/editar/{id}")
    public String editarEmpleoView(@PathVariable Long id, Model model){
        Empleo editar = empleosService.obtenerEmpleoPorId(id);
        model.addAttribute("empleo", editar);
        return "/empleos/editarEmpleo";
    }
    @PostMapping("/editar/submit")
    public String showEdit(Empleo empleo){
        empleosService.editarEmpleo(empleo);
        return "redirect:/empleosDisponibles";
    }
}
