package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {
    @Autowired 
    WorklineService worklineService;
    @GetMapping({"/inicio","/index","/"})
    public String showInicio(Model model){
        model.addAttribute("allo", worklineService.obtAllo());
        return "inicio";
    }
    @GetMapping("/quienesSomos")
    public String showQuienesSomos(Model model){   
        model.addAttribute("listaEmpresas", worklineService.displayEmpresas());
        return "quienesSomos";
    }
    @GetMapping("/iniciarSesion")
    public String showIniciarSesion(Model model){
        model.addAttribute("estacion", worklineService.obtMes());
        return "iniciarSesion";
    }
    @GetMapping("/enviarDatos")
    public String showCuestionario(Model model){
        model.addAttribute("formulario",new WorklineFormulario());
        return "formHtml";
    }
    @PostMapping("/enviarDatos/resultado")
    public String showSubmit(WorklineFormulario worklineFormulario,Model model){
        model.addAttribute("rNombre",worklineFormulario.getNombre());
        model.addAttribute("rSector",worklineFormulario.getSector());
        model.addAttribute("rTrabajo", worklineFormulario.getTrabajo());
        model.addAttribute("rJornada", worklineFormulario.getJornadaCompleta());
        return "submitHtml";
    }
}
