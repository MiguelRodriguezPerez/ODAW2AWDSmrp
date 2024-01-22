package com.example.myshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myshop.domain.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria,Long>{
    
}
