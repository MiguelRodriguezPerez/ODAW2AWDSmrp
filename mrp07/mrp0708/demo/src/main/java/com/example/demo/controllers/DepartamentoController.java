package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Departamento;
import com.example.demo.services.DepartamentoService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/departamentos")
public class DepartamentoController {
    
    @Autowired
    DepartamentoService departamentoService;

    @GetMapping({"/inicio","/"})
    public String showDepartamentos(Model model){
        model.addAttribute("listaDepartamentos", departamentoService.obtenerTodos());
        return "departamentoFolder/departamentosView";
    }

    @GetMapping("/nuevoDepartamento")
    public String showDepartamentosNew(Model model){
        model.addAttribute("departamento", new Departamento());
        model.addAttribute("listaDepartamentos", departamentoService.obtenerTodos());
        return "departamentoFolder/departamentoNew";
    }

    @GetMapping("/nuevoDepartamento/submit")
    public String showDepartamentosNewSubmit(@Valid Departamento departamento, BindingResult bindingResult){
        if(bindingResult.hasErrors()) return "departamentoFolder/departamentoError";
        departamentoService.nuevoDepartamento(departamento);
        return "redirect:/departamentos/inicio";
    }

    @GetMapping("/editar/{id}")
    public String showDepartamentosEdit(@PathVariable Long id, Model model){
        Departamento departamento = departamentoService.obtenerDepartamentoPorId(id);
        if(departamento != null){
            
            model.addAttribute("departamentoEditado", departamento);
            return "departamentoFolder/departamentosEdit";
        }
        else return "redirect:/departamentos/inicio";
    }

    @PostMapping("/editar/submit")
    public String showDepartamentoEditSubmit(@Valid Departamento departamento, BindingResult bindingResult){
        if(bindingResult.hasErrors()) return "departamentoFolder/errorDepartamento";
        departamentoService.editarDepartamento(departamento);
        return "redirect:/departamentos/inicio";
    }

    @GetMapping("/borrar/{id}")
    public String showDepartamentoDelete(@PathVariable Long id){
        departamentoService.borrar(id);
        return "redirect:/departamentos/inicio";
    }
}
