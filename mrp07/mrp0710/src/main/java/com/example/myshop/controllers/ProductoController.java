package com.example.myshop.controllers;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.myshop.domain.Categoria;
import com.example.myshop.domain.Producto;
import com.example.myshop.services.CategoriaService;
import com.example.myshop.services.ProductoService;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    public ProductoService productoService;

    @Autowired
    public CategoriaService categoriaService;

    @GetMapping({ "", "/" })
    public String showList(Model model) {
        model.addAttribute("listaProductos", productoService.obtenerTodos());
        model.addAttribute("listaCategorias", categoriaService.obtenerTodos());
        model.addAttribute("categoriaSeleccionada", new Categoria(0L, "Todas"));
        return "product/productListView";
    }

        @GetMapping("/porCateg/{idCat}")
    public String showListInCategory(@PathVariable Long idCat, Model model) {
        model.addAttribute("listaProductos", productoService.obtenerPorCategoria(idCat));
        model.addAttribute("listaCategorias", categoriaService.obtenerTodos());
        model.addAttribute("categoriaSeleccionada", categoriaService.obtenerPorId(idCat));
        return "product/productListView";
    }

    @GetMapping("/new")
    public String showNew(Model model) {
        model.addAttribute("productoForm", new Producto());
         model.addAttribute("listaCategorias", categoriaService.obtenerTodos());
        return "product/productNewView";
    }

    @PostMapping("/new/submit")
    public String showNewSubmit(
            @Valid Producto productoForm,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "product/productNewView";
        productoService.añadir(productoForm);
        return "redirect:/productos/";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable long id, Model model) {
        Producto producto = productoService.obtenerPorId(id);
        if (producto != null) {
            model.addAttribute("productoForm", producto);
             model.addAttribute("listaCategorias", categoriaService.obtenerTodos());
            return "product/productEditView";
        } else {
            return "redirect:/productos/";
        }
    }

    @PostMapping("/edit/submit")
    public String showEditSubmit(@Valid Producto productoForm,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "product/productEditView";
        } else {
            productoService.editar(productoForm);
            return "redirect:/productos/";
        }
    }

    @GetMapping("/delete/{id}")
    public String showDelete(@PathVariable long id) {
        productoService.borrar(id);
        return "redirect:/productos/";
    }


}
