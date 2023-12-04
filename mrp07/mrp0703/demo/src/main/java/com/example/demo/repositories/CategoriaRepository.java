package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Categoria;
import java.util.Optional;


public interface CategoriaRepository extends JpaRepository<Categoria,Long>{
    Optional<Categoria> findByIdCategoria(Long id);
    Categoria findByNombre(String nombre);
}

