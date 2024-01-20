package com.example.myshop.rest_controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myshop.domain.Usuario;
import com.example.myshop.services.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioRestController {
    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/")
    public List<Usuario> getAllUsuarios(){
        return usuarioService.obtenerTodos();
    }

    @PostMapping("/nuevoUsuario")
    public ResponseEntity<?> newUsuario(@Valid @RequestBody Usuario usuario){
        usuarioService.guardar(usuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @PutMapping("/modificarUsuario")
    public Usuario editUsuario(@Valid @RequestBody Usuario usuario){
        return usuarioService.editar(usuario);
    }
}
