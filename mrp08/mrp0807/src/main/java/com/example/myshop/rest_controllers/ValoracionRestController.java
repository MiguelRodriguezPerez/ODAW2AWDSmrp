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

import com.example.myshop.domain.Valoracion;
import com.example.myshop.services.ProductoService;
import com.example.myshop.services.UsuarioService;
import com.example.myshop.services.ValoracionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/valoracion")
public class ValoracionRestController {
    //Get para más tarde
    @Autowired
    ValoracionService valoracionService;
    @Autowired
    ProductoService productoService;
    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/porProducto/{id}")
    public List<Valoracion> valoracionesByProducto(@PathVariable Long id){
        return valoracionService.obtenerValoracionesPorProducto(productoService.obtenerPorId(id));
    }

    @GetMapping("/porUsuario/{id}")
    public List<Valoracion> valoracionesByUsuario(@PathVariable Long id){
        return valoracionService.obtenerValoracionesPorUsuario(usuarioService.obtenerPorId(id));
    }

    @PostMapping("/nuevaValoracion")
    public ResponseEntity<?> newValoracion(@Valid @RequestBody Valoracion valoracion){
        valoracionService.guardar(valoracion);
        return ResponseEntity.status(HttpStatus.CREATED).body(valoracion);
    }

    @PutMapping("/editarValoracion")
    public Valoracion editValoracion(@Valid @RequestBody Valoracion valoracion){
        return valoracionService.editar(valoracion);
    }

    @DeleteMapping("/borrarValoracion")
    public ResponseEntity<?> deleteValoracion(@PathVariable long id){
        valoracionService.borrar(id);
        return ResponseEntity.noContent().build();
    }
}
