package com.example.demo;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ControladorDos {
    @GetMapping({"/index","/home","/"})
    public String indexView(@RequestParam(name = "cliente",required=false) String usuario, Model model){
        /*@RequestParam recibe od argumentos, el nombre de la variable que se pedirá en
        la url y required=false que significa que no será obligatorio escribirlo en la url
        String usuario guardará el valor que se escriba en la url*/
        model.addAttribute("customer",usuario);
        /*addAttribute() contiene dos argumentos: el nombre de la variable que
          se usará en el html y el valor de dicha variable, en este caso el usuario
          que recibió en la url*/
        model.addAttribute("ayo","©"+Integer.toString(LocalDate.now().getYear()));
        return "indexView";
    }
    @GetMapping("/about-us")
    public String quienesSomosView(){
        return "quienes_somosView";
    }
    @GetMapping("/contacta")
    public String contactaView(Model model){
        model.addAttribute("formulario", new Formulario());
        return "contactaView";
    }
    @PostMapping("/contacta/resultado")
    public String contactaSubmitView(Formulario formulario,Model model){
        model.addAttribute("nombre", formulario.getNombre());
        model.addAttribute("email", formulario.getEmail());
        model.addAttribute("opcion", formulario.getOpcion());
        model.addAttribute("comentario", formulario.getComentario());
        model.addAttribute("condiciones", formulario.isCondiciones());
        return "contactaSubmit";
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
        return "productosView";
    }
}
