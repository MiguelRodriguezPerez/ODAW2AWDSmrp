package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ControllerFormulario {
    @GetMapping("/formulario")//Recibe los datos del formulario
    public String showFormulario(Model model){
        model.addAttribute("formularioModel", new Formulario());
        return "index";
    }
    @PostMapping("/formulario/subir")//Trata los datos del formulario
    public String showSubirFormulario(Formulario formulario){
        //Aquí se tratarían los datos
        return "resultado";
    }
}
