package com.example.myshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.myshop.domain.Valoracion;
import com.example.myshop.services.ProductoService;
import com.example.myshop.services.UsuarioService;
import com.example.myshop.services.ValoracionService;

@Controller
@RequestMapping("/valoraciones")
public class ValoracionController {

    @Autowired 
    ValoracionService valoracionService;

    @Autowired
    ProductoService productoService;

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/porProducto/{id}")
    public String showValoracionesPorProducto(@PathVariable Long id, Model model){
        model.addAttribute("producto", productoService.obtenerPorId(id).getNombre());
        model.addAttribute("listaOpiniones", valoracionService.obtenerValoracionesPorProducto(productoService.obtenerPorId(id)));
        return "rating/ratingPorProducto";
    }

    @GetMapping("/porUsuario/{id}")
    public String showValoracionesPorUsuario(@PathVariable Long id, Model model){
        model.addAttribute("usuario", usuarioService.obtenerPorId(id).getNombre());
        model.addAttribute("listaOpiniones", valoracionService.obtenerValoracionesPorUsuario(usuarioService.obtenerPorId(id)));
        return "rating/ratingPorUsuario";
    }

    @GetMapping("/nuevaValoracion")
    public String showValoracionesNew(Model model){
        model.addAttribute("nuevaValoracion", new Valoracion());
        model.addAttribute("listaUsuarios", usuarioService.obtenerTodos());
        model.addAttribute("listaProductos", productoService.obtenerTodos());
        return "rating/ratingNewView";
    }
    
    @PostMapping("/nuevaValoracion/submit")
    public String showValoracionesNewSubmit(Valoracion valoracion){
        valoracionService.guardar(valoracion);
        return "redirect:/valoraciones/porUsuario/{" + valoracion.getUsuario().getId() +"}";
    }

    @GetMapping("/borrar/{id}")
    public String showValoracionesDelete(@PathVariable Long id){
        valoracionService.borrar(id);
        return "main/homeView";
    }
}
