package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Departamento;
import java.util.Optional;


public interface DepartamentoRepository extends JpaRepository<Departamento,Long>{
    Optional<Departamento> findById(Long id);
    Departamento findByNombre(String nombre);
}
