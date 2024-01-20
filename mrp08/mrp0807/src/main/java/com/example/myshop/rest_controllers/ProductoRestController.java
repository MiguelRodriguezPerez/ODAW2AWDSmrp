package com.example.myshop.rest_controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myshop.domain.Producto;
import com.example.myshop.services.CategoriaService;
import com.example.myshop.services.ProductoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/producto")
public class ProductoRestController {

    @Autowired
    ProductoService productoService;
    @Autowired
    CategoriaService categoriaService;

    @GetMapping("/")
    public List<Producto> showProductos(){
        return productoService.obtenerTodos();
    }

    @PostMapping("/nuevoProducto")
    public ResponseEntity<?> guardarNuevoProducto(@Valid @RequestBody Producto pr){
        return ResponseEntity.status(HttpStatus.CREATED).body(pr);
    }

    @PutMapping("/editarProducto/{id}")
    public Producto editarProducto(@PathVariable Long id, @RequestBody Producto edProducto){
        productoService.obtenerPorId(id);

        return productoService.editar(edProducto);
    }

    @DeleteMapping("/borrarProducto/{id}")
    public ResponseEntity<?> deleteProducto(@PathVariable Long id){
        productoService.obtenerPorId(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/productosPorCategoria/{id}")
    public List<Producto> getProductoByCategoria(@PathVariable Long id){
        categoriaService.obtenerPorId(id);
        return productoService.obtenerPorCategoria(id);
    }
}
