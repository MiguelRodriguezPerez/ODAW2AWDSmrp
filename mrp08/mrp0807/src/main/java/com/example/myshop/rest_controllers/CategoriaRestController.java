package com.example.myshop.rest_controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myshop.domain.Categoria;
import com.example.myshop.services.CategoriaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaRestController {
    @Autowired
    public CategoriaService categoriaService;

    @GetMapping({ "/","/inicio" })
    public List<Categoria> showList() {
        return categoriaService.obtenerTodos();
    }

    @PostMapping("/nuevaCategoria")
    public Categoria showNuevaCategoria(@Valid @RequestBody Categoria categoria) {
        return categoriaService.añadir(categoria);
    }

    @PutMapping("/editar/{id}")
    public Categoria showEditarCategoria(@Valid @RequestBody Categoria categoriaF, @PathVariable Long id){
        return categoriaService.editar(categoriaF);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategoria(@PathVariable long id) {
        categoriaService.borrar(id);
        return ResponseEntity.noContent().build();
    }
}
