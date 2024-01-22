package com.example.myshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myshop.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Long>{
    
}
