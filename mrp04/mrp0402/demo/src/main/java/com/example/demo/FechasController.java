package com.example.demo;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/fechas")
public class FechasController {
    @Autowired
    FechaService fechaService;

    @GetMapping("/{f1}")
    public String show2B(@PathVariable LocalDate f1,Model model){
        model.addAttribute("f1Parametro", f1);
        model.addAttribute("f1Resultado",fechaService.desdeUno(f1));
        return "index";
    }
    @GetMapping("/{f2}/{f3}")
    public String show2C(@PathVariable LocalDate f2, @PathVariable LocalDate f3, Model model){
        model.addAttribute("f2Parametro", f2);
        model.addAttribute("f3Parametro", f3);
        model.addAttribute("f2Resultado",fechaService.entreDosFechas(f2, f3));
        return "index";
    }
    @GetMapping("/")
    public String show2D(Model model){
        model.addAttribute("f3Resultado", fechaService.desdeUno(LocalDate.now()));
        return "index";
    }
    @GetMapping("/bisiesto/{f4}")
    public String show2E(@PathVariable LocalDate f4,Model model){
        model.addAttribute("f4Parametro", f4);
        model.addAttribute("f4Resultado", fechaService.esBisiesto(f4));
        return "index";
    }
    @GetMapping("/bisiesto/{alloUno}/{alloDos}")
    public String show2F(@PathVariable Integer alloUno,@PathVariable Integer alloDos,Model model){
        model.addAttribute("alloUnoParametro",alloUno);
        model.addAttribute("alloDosParametro", alloDos);
        model.addAttribute("alloResultado", fechaService.esBisiestoEntreDos(alloUno, alloDos));
        return "index";
    }
}
