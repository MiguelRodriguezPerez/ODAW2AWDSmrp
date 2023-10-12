package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller//Indica que esta clase será un controlador
public class ControladorUno {
    @GetMapping({"/index","/home","/"})
    /*GetMapping contiene la ruta o rutas url que recibirá para devolver index.html
      En este caso, devolverá index si en la url se busca "/index","/home"
      o simplemente"/"*/
    public String indexView(){
        return "indexView";/*El archivo html que devolverá sin su extensión html */
    }
    @GetMapping("/about-us")
    public String quienesSomosView(){
        return "aboutUsView";
    }
    @GetMapping("/contact")
    public String contactaView(){
        return "contactView";
    }
    @GetMapping("/products")
    public String productosView(){
        return "productsView";
    }
}
