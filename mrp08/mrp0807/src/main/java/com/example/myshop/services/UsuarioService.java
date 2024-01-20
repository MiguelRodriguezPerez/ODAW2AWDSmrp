package com.example.myshop.services;

import java.util.List;

import com.example.myshop.domain.Usuario;

public interface UsuarioService {
    Usuario guardar(Usuario usuario);
    List<Usuario> obtenerTodos();
    Usuario obtenerPorId(Long id);
    Usuario editar(Usuario usuario);
    void borrar(Long id);
}
