package com.example.myshop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myshop.domain.Usuario;
import com.example.myshop.repositories.UsuarioRepository;

@Service
public class UsuarioServiceImplMem implements UsuarioService{
    @Autowired
    UsuarioRepository usuarioRepository;

    public Usuario añadir(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> obtenerTodos(){
        return usuarioRepository.findAll();
    }

    public Usuario obtenerPorId(Long id){
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario editar(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public void borrar(Long id){
        usuarioRepository.deleteById(id);
    }
}
