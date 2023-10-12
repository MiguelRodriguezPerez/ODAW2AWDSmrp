package com.example.demo;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping({"/inicio","/index","/"})
    public String showInicio(Model model){
        model.addAttribute("allo", Integer.toString(LocalDate.now().getYear()));
        return "inicio";
    }
    @GetMapping("/quienesSomos")
    public String showQuienesSomos(Model model){
        ArrayList<String> empresas = new ArrayList<>();
        empresas.add("Zara");
        empresas.add("Indra");
        empresas.add("Ford");
        empresas.add("Carrefour");
        empresas.add("Ikea");
        
        model.addAttribute("listaEmpresas", empresas);
        return "quienesSomos";
    }
    @GetMapping("/iniciarSesion")
    public String showIniciarSesion(Model model){
        model.addAttribute("estacion", LocalDate.now().getMonthValue());
        return "iniciarSesion";
    }
}
