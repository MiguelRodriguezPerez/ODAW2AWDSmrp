package com.example.clinica.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.clinica.domain.Paciente;
import com.example.clinica.domain.PacienteDTO;
import com.example.clinica.services.PacienteService;

import jakarta.validation.Valid;

@Controller
public class PacienteController {
    
    @Autowired
    PacienteService pacienteService;

    @GetMapping("/")
    public String showList(Model model) {
        model.addAttribute("listaPacientes", pacienteService.findAll());
        model.addAttribute("factura", 0);
        return "pacientesListView";
    }

    @GetMapping("/newPaciente")
    public String showNew(Model model) {
        model.addAttribute("newPaciente", new PacienteDTO());
        return "pacienteNewView";
    }

    @PostMapping("/newPaciente/submit")
    public String showNewSubmit(@Valid PacienteDTO newPacienteDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            Paciente newPaciente = pacienteService.buildPacienteFromDTO(newPacienteDTO);
            pacienteService.add(newPaciente);
        }
        return "redirect:/";
    }
    
    
    @GetMapping("/delete")
    public String showDelete(Model model) {
        Paciente paciente = pacienteService.getFirst();
        Double factura = 0d;
        if (paciente != null) {
            factura = pacienteService.facturar(paciente);
            pacienteService.deleteFirst();
        }
        model.addAttribute("listaPacientes", pacienteService.findAll());
        model.addAttribute("factura", factura);
        return "PacientesListView";
    }
    

}
