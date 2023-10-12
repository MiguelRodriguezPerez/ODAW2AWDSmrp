package com.example.demo;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorDos {
    @GetMapping({"/index","/home","/"})
    public String indexView(Model model){
        //Model añade valores que podrán ser insertados en el html
        model.addAttribute("ayo","©"+Integer.toString(LocalDate.now().getYear()));
        /*addAttribute() contiene dos argumentos: el nombre de la variable que
          se usará en el html y el valor de dicha variable*/
        return "index";
    }
    @GetMapping("/about-us")
    public String quienesSomosView(){
        return "quienes_somos";
    }
    @GetMapping("/contact")
    public String contactaView(){
        return "contacta";
    }
    @GetMapping("/products")
    public String productoView(Model model){
        //Model añade valores que podrán ser insertados en el html
        ArrayList<String>lista=new ArrayList<>();//Se crea el arrayList
        lista.add("Barril");//Se le añaden valores
        lista.add("Caja");
        lista.add("Contenendor");
        lista.add("Remolque");
        lista.add("Almacen");

        model.addAttribute("elementos", lista);
        /*addAttribute() contiene dos argumentos: el nombre de la variable que
          se usará en el html y el valor de dicha variable
          En este caso, el nombre que usará en el html será elementos, y el valor
          que contendrá elementos será el arrayList lista*/
        return "productos";
    }
}
