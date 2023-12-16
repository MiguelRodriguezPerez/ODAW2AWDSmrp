package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Proyecto;

public interface ProyectoRepository extends JpaRepository<Proyecto,Long>{
    Optional<Proyecto> findById(Long id);
    Proyecto findByNombre(String nombre);
}
