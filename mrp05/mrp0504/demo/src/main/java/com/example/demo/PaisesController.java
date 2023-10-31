package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PaisesController {
    @Autowired
    PaisesServicio paisesServicio;
    @GetMapping("/")
    public String showPaises(Model model){
        model.addAttribute("formInfo", new FormInfo());
        model.addAttribute("listaPaisesNombres", paisesServicio.getPaisesNombres());
        return "placeholder";
    }
    @PostMapping("/")
    public String showNewSubmit(FormInfo formInfo, Model model){
        if(formInfo.getNombre().equals("")) return "redirect:/";
        model.addAttribute("formInfo", formInfo);
        model.addAttribute("listaPaisesNombres", paisesServicio.getPaisesNombres());

        Pais pais = paisesServicio.getPais(formInfo.getNombre());

        model.addAttribute("nombrePais", pais.getNombrePais());
        model.addAttribute("capital", pais.getCapital());
        model.addAttribute("poblacion", pais.getPoblacion());
        return "placeholder";
    }
}
