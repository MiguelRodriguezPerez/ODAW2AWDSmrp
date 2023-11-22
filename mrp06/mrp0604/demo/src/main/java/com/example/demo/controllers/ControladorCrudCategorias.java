package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.domain.Categoria;
import com.example.demo.services.CategoriaService;

@Controller
public class ControladorCrudCategorias {

    @Autowired
    CategoriaService categoriaService;
    
    @GetMapping("/categoria")
    public String showIndex(Model model){
        model.addAttribute("categorias", categoriaService.verCategorias());
        return "categorias/categoriasView";
    }

    @GetMapping("/nuevaCategoria")
    public String showNuevaCategoria(Model model){
        model.addAttribute("categoria", new Categoria());
        return "categorias/nuevaCategoria";
    }

    @PostMapping("/nuevaCategoria/submit")
    public String showNuevaCategoriaSubmit(Categoria categoria){
        categoriaService.addCategoria(categoria);
        return "redirect:/categoria";
    }
    
    @GetMapping("/editarCategoria/{cat}")
    public String showEditarCategoria(@PathVariable Long cat, Model model){
        Categoria categoria = categoriaService.obtenerCategoriaPorId(cat);
        if(categoria!=null){
            model.addAttribute("categoria", categoria);
            return "/categorias/editarCategoria";   
        } 
        return "redirect:/categoria";
    }

    @PostMapping("/editarCategoria/submit")
    public String showEditarCategoriaSubmit(Categoria categoria){
        categoriaService.editarCategoria(categoria);
        return "redirect:/categoria";
    }

    @GetMapping("/borrarCategoria/{cat}")
    public String showBorrarCategoria(@PathVariable Long cat){
        categoriaService.borrarCategoria(cat);
        return "redirect:/categoria";
    }
}
