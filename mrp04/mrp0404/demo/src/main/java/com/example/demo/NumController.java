package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.annotation.SessionScope;



@Controller
@SessionScope
public class NumController {
    @Autowired
    NumServicio numServicio;

    @GetMapping({"/","/list",""})
    public String showList(Model model){
        model.addAttribute("cantidadTotal", numServicio.obtLista().size());
        model.addAttribute("listaNumeros", numServicio.obtLista());
        return "listView";
    }
    @GetMapping("/new")
    public String showNew(){
        numServicio.nuevoNumero();
        return "redirect:/list";
    }
    @GetMapping("/delete/{id}")
    public String showDelete(@PathVariable Integer id){
        numServicio.borrarNumero(id);
        return "redirect:/list";
    }
}
